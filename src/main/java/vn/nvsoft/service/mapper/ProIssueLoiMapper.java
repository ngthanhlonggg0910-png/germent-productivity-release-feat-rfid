package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProIssueLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProIssueLoi} and its DTO {@link ProIssueLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProIssueCumLoiMapper.class})
public interface ProIssueLoiMapper extends EntityMapper<ProIssueLoiDTO, ProIssueLoi> {

    @Mapping(source = "proIssueCumLoi.id", target = "proIssueCumLoiId")
    @Mapping(source = "proIssueCumLoi.ten", target = "proIssueCumLoiTen")
    ProIssueLoiDTO toDto(ProIssueLoi proIssueLoi);

    @Mapping(source = "proIssueCumLoiId", target = "proIssueCumLoi")
    ProIssueLoi toEntity(ProIssueLoiDTO proIssueLoiDTO);

    default ProIssueLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProIssueLoi proIssueLoi = new ProIssueLoi();
        proIssueLoi.setId(id);
        return proIssueLoi;
    }
}
