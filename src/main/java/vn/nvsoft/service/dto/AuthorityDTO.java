package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.Authority;

public class AuthorityDTO implements Serializable {

    private String name;

    public AuthorityDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorityDTO(Authority authority) {
        this.name = authority.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorityDTO that = (AuthorityDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
            "name='" + name + '\'' +
            '}';
    }
}
