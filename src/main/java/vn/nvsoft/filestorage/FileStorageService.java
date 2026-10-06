package vn.nvsoft.filestorage;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    List<String> loadAllFileName();

    Stream<Path> loadAllFilePath();

    Optional<FileStorageVM> store(MultipartFile file);

    Path load(String filename);

    void deleteAll();

    void deleteFile(String filename);

    Resource loadAsResource(String filename);
}
