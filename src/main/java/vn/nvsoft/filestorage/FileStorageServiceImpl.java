package vn.nvsoft.filestorage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final StorageProperties storageProperties;

    private final FileStorageHelper fileStorageHelper;

    private final Path storageRootDirectory;

    public FileStorageServiceImpl(FileStorageHelper fileStorageHelper,
        StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
        this.fileStorageHelper = fileStorageHelper;
        this.storageRootDirectory = Paths.get(storageProperties.getDirectory());
    }

    @Override
    public Optional<FileStorageVM> store(MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new FileStorageException(
                    "Cannot store file with relative path outside current directory "
                        + filename);
            }

            LocalDate currentDate = LocalDate.now();
            Path storageDir = fileStorageHelper.getStorageDir(currentDate, storageRootDirectory);
            String cleanFilename = fileStorageHelper.handleFileName(filename, storageDir)
                .orElseThrow(() -> new FileStorageException("Invalid File Extension"));

            Files.copy(file.getInputStream(), storageDir.resolve(cleanFilename));

            String extension = fileStorageHelper.getFileExtension(cleanFilename);
            String fileBaseName = fileStorageHelper.getFileBaseName(cleanFilename, extension);
            String fileUri = fileStorageHelper.getFileUri(currentDate, cleanFilename);

            FileStorageVM result = new FileStorageVM()
                .fileName(cleanFilename)
                .fileHost(storageProperties.getHostUrl())
                .fileUri(fileUri)
                .fileDownloadUri(new StringBuilder(storageProperties.getHostUrl())
                    .append("/")
                    .append(fileUri)
                    .toString())
                .fileBaseName(fileBaseName)
                .fileExtension(extension)
                .size(file.getSize())
                .uploadedAt(Instant.now());
            return Optional.of(result);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(storageRootDirectory.toFile());
    }

    @Override
    public Path load(String filename) {
        return storageRootDirectory.resolve(filename);
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path file = load(filename);
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadAllFileName() {
        List<String> results = new ArrayList<>();
        try {
            Files.walk(this.storageRootDirectory, 1).filter(Files::isRegularFile).forEach(filePath ->{
                String name = filePath.getFileName().toString();
                results.add(name);
            });
            return results;
        } catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Stream<Path> loadAllFilePath() {
        try {
            return Files.walk(storageRootDirectory, 1)
                .filter(path -> !path.equals(this.storageRootDirectory))
                .map(this.storageRootDirectory::relativize);
        } catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(
                    "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }
}
