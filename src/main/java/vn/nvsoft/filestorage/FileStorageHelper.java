package vn.nvsoft.filestorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FileStorageHelper {
    public static final String FILE_NAME_REGEX = "[^A-Za-z0-9.()]";

    private final StorageProperties storageProperties;

    public FileStorageHelper(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    public void createDirectoryIfNeeded(Path directory) throws IOException {
        if (!directory.toFile().exists()) {
            Files.createDirectories(directory);
        }
    }

    public boolean isValidExtension(String fileName) {
        String fileExtension = getFileExtension(fileName);

        if (fileExtension == null) {
            return false;
        }

        fileExtension = fileExtension.toLowerCase();

        for (String validExtension : storageProperties.getExtensions().split(",")) {
            if (fileExtension.equals(validExtension)) {
                return true;
            }
        }
        return false;
    }

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) {
            return null;
        }
        return fileName.substring(dotIndex + 1);
    }

    public Optional<String> handleFileName(String fileName, Path uploadDirectory) {

        String cleanFileName = fileName.replaceAll(FileStorageHelper.FILE_NAME_REGEX, "");
        String extension = getFileExtension(cleanFileName);

        if (!isValidExtension(cleanFileName)) {
            return Optional.empty();
        }

        String base = cleanFileName.substring(0, cleanFileName.length() - extension.length() - 1);
        int openParIndex = getOpenParenthesisIndex(base);
        if (openParIndex > 0) {
            base = base.substring(0, openParIndex);
            cleanFileName = base + "." + extension;
        }

        if (uploadDirectory.resolve(cleanFileName).toFile().exists()) {
            cleanFileName = base + "(1)." + extension;
        }

        while (uploadDirectory.resolve(cleanFileName).toFile().exists()) {
            String nString = cleanFileName.substring(
                base.length() + 1,
                cleanFileName.length() - extension.length() - 2
            );
            int n = Integer.parseInt(nString) + 1;
            cleanFileName = base + "(" + n + ")." + extension;
        }

        return Optional.of(cleanFileName);
    }

    public int getOpenParenthesisIndex(String baseFileName) {
        int openParIndex = baseFileName.lastIndexOf("(");
        int closeParIndex = baseFileName.lastIndexOf(")");
        boolean isParenthesis = openParIndex > 0 &&
            closeParIndex == baseFileName.length() - 1;

        if (isParenthesis && baseFileName.substring(openParIndex + 1, closeParIndex)
            .matches("[1-9][0-9]*")) {
            return openParIndex;
        } else {
            return -1;
        }
    }

    public String getFileBaseName(String cleanFileName, String extension) {
        return cleanFileName.substring(0, cleanFileName.length() - extension.length() - 1);
    }

    public Path getStorageDir(LocalDate currentDate, Path rootDir) {
        String currentMonth = currentDate.getMonth().toString(); // JUNE
        String currentYear = Integer.toString(currentDate.getYear()); // 2019
        String currentDay = Integer.toString(currentDate.getDayOfMonth()); // 16

        Path result = rootDir
            .resolve(currentYear)
            .resolve(currentMonth)
            .resolve(currentDay);

        try {
            createDirectoryIfNeeded(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getFileUri(LocalDate currentDate, String fileName) {
        String currentMonth = currentDate.getMonth().toString(); // JUNE
        String currentYear = Integer.toString(currentDate.getYear()); // 2019
        String currentDay = Integer.toString(currentDate.getDayOfMonth()); // 16
        return new StringBuilder()
            .append(currentYear)
            .append("/")
            .append(currentMonth)
            .append("/")
            .append(currentDay)
            .append("/")
            .append(fileName).toString();
    }
}
