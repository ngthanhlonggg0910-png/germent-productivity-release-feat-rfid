package vn.nvsoft.filestorage;

import java.time.Instant;
import javax.validation.constraints.NotBlank;

public class FileStorageVM {

    @NotBlank
    private String fileName; // file(1).txt

    private String fileHost; // http://0.0.0.0:4321

    private String fileUri; // 2019/OCTOBER/16/file(1).txt

    private String fileDownloadUri; // http://0.0.0.0:4321/2019/OCTOBER/16/file(1).txt

    @NotBlank
    private String fileExtension; // txt

    private String fileBaseName; // file.txt

    private long size;

    private Instant uploadedAt;

    public FileStorageVM() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileStorageVM size(long size) {
        this.size = size;
        return this;
    }

    public FileStorageVM fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileStorageVM fileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
        return this;
    }

    public FileStorageVM fileType(int size) {
        this.size = size;
        return this;
    }

    public FileStorageVM fileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public FileStorageVM fileHost(String fileHost) {
        this.fileHost = fileHost;
        return this;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public FileStorageVM fileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public String getFileBaseName() {
        return fileBaseName;
    }

    public void setFileBaseName(String fileBaseName) {
        this.fileBaseName = fileBaseName;
    }

    public FileStorageVM fileBaseName(String fileBaseName) {
        this.fileBaseName = fileBaseName;
        return this;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public FileStorageVM uploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
        return this;
    }

    @Override
    public String toString() {
        return "FileStorageVM{" +
            "fileName='" + fileName + '\'' +
            ", fileHost='" + fileHost + '\'' +
            ", fileUri='" + fileUri + '\'' +
            ", fileDownloadUri='" + fileDownloadUri + '\'' +
            ", fileExtension='" + fileExtension + '\'' +
            ", fileBaseName='" + fileBaseName + '\'' +
            ", size=" + size +
            ", uploadedAt=" + uploadedAt +
            '}';
    }
}
