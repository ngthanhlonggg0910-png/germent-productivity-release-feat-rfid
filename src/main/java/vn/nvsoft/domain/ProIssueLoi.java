package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProIssueLoi.
 */
@Entity
@Table(name = "pro_issue_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProIssueLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_loi")
    private String maLoi;

    @Column(name = "cus_code")
    private String cusCode;

    @Column(name = "ten_loi")
    private String tenLoi;

    @Column(name = "ten_tieng_anh")
    private String tenTiengAnh;

    @Column(name = "label")
    private String label;

    @Column(name = "stt")
    private Integer stt;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proIssueLois")
    private ProIssueCumLoi proIssueCumLoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public ProIssueLoi maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getCusCode() {
        return cusCode;
    }

    public ProIssueLoi cusCode(String cusCode) {
        this.cusCode = cusCode;
        return this;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public ProIssueLoi tenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
        return this;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getTenTiengAnh() {
        return tenTiengAnh;
    }

    public ProIssueLoi tenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
        return this;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    public String getLabel() {
        return label;
    }

    public ProIssueLoi label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStt() {
        return stt;
    }

    public ProIssueLoi stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMauSac() {
        return mauSac;
    }

    public ProIssueLoi mauSac(String mauSac) {
        this.mauSac = mauSac;
        return this;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public ProIssueLoi ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ProIssueCumLoi getProIssueCumLoi() {
        return proIssueCumLoi;
    }

    public ProIssueLoi proIssueCumLoi(ProIssueCumLoi proIssueCumLoi) {
        this.proIssueCumLoi = proIssueCumLoi;
        return this;
    }

    public void setProIssueCumLoi(ProIssueCumLoi proIssueCumLoi) {
        this.proIssueCumLoi = proIssueCumLoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProIssueLoi)) {
            return false;
        }
        return id != null && id.equals(((ProIssueLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProIssueLoi{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", cusCode='" + getCusCode() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            ", label='" + getLabel() + "'" +
            ", stt=" + getStt() +
            ", mauSac='" + getMauSac() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
