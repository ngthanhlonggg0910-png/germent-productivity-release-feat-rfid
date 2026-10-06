package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProIssueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProIssue} and its DTO {@link ProIssueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProIssueMapper extends EntityMapper<ProIssueDTO, ProIssue> {



    default ProIssue fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProIssue proIssue = new ProIssue();
        proIssue.setId(id);
        return proIssue;
    }
}
