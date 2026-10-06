package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SysFile} entity.
 */
public class SysFileDTO implements Serializable {

    private Long id;

    private String fileName;

    private String fileUri;

    private String fileExtension;

    private Long size;

    private Instant uploadedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysFileDTO sysFileDTO = (SysFileDTO) o;
        if (sysFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysFileDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", fileUri='" + getFileUri() + "'" +
            ", fileExtension='" + getFileExtension() + "'" +
            ", size=" + getSize() +
            ", uploadedAt='" + getUploadedAt() + "'" +
            "}";
    }
}
