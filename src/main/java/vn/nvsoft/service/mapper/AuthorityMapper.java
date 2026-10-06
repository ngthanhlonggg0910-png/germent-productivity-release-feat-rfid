package vn.nvsoft.service.mapper;

import org.mapstruct.Mapper;
import vn.nvsoft.domain.Authority;
import vn.nvsoft.service.dto.AuthorityDTO;

/**
 * Mapper for the entity {@link Authority} and its DTO {@link AuthorityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapper extends EntityMapper<AuthorityDTO, Authority> {

    default Authority fromName(String name) {
        if (name == null) {
            return null;
        }
        Authority authority = new Authority();
        authority.setName(name);
        return authority;
    }
}
