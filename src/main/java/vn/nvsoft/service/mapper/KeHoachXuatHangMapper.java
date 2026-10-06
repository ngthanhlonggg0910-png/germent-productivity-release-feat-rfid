package vn.nvsoft.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.nvsoft.domain.Device;
import vn.nvsoft.service.dto.DeviceDTO;
import vn.nvsoft.service.viewmodel.KeHoachXuatHang;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;

@Mapper(componentModel = "spring", uses = {})
public interface KeHoachXuatHangMapper {

    @Mapping(source = "PONo", target = "pono")
    KeHoachXuatHangDTO toDto(KeHoachXuatHang entity);
}
