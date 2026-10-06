package vn.nvsoft.filestorage;

import org.springframework.stereotype.Service;
import vn.nvsoft.service.dto.SysFileDTO;

@Service
public class FileStorageMapper {

    public SysFileDTO fileVMToSysFileDTO(FileStorageVM fileVM) {
        SysFileDTO sysFileDTO = new SysFileDTO();
        sysFileDTO.setFileName(fileVM.getFileName());
        sysFileDTO.setFileUri(fileVM.getFileUri());
        sysFileDTO.setFileExtension(fileVM.getFileExtension());
        sysFileDTO.setSize(fileVM.getSize());
        sysFileDTO.setUploadedAt(fileVM.getUploadedAt());
        return sysFileDTO;
    }
}
