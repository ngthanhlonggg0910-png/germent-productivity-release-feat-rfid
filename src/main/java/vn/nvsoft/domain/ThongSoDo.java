package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ThongSoDo.
 */
@Entity
@Table(name = "thong_so_do")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ThongSoDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "ten")
    private String ten;

    @Column(name = "quy_cach")
    private String quyCach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDos")
    private DungSai dungSai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ThongSoDo code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTen() {
        return ten;
    }

    public ThongSoDo ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public DungSai getDungSai() {
        return dungSai;
    }

    public ThongSoDo dungSai(DungSai dungSai) {
        this.dungSai = dungSai;
        return this;
    }

    public void setDungSai(DungSai dungSai) {
        this.dungSai = dungSai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public String getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(String quyCach) {
        this.quyCach = quyCach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongSoDo)) {
            return false;
        }
        return id != null && id.equals(((ThongSoDo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ThongSoDo{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
