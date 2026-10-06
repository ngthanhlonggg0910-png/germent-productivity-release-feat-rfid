package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProLoi.
 */
@Entity
@Table(name = "pro_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProLoi implements Serializable {

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

    @Column(name = "qc")
    private Boolean qc;

    @Column(name = "qa")
    private Boolean qa;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proLois")
    private ProCumLoi proCumLoi;

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

    public ProLoi maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getCusCode() {
        return cusCode;
    }

    public ProLoi cusCode(String cusCode) {
        this.cusCode = cusCode;
        return this;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public ProLoi tenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
        return this;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getTenTiengAnh() {
        return tenTiengAnh;
    }

    public ProLoi tenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
        return this;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    public String getLabel() {
        return label;
    }

    public ProLoi label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStt() {
        return stt;
    }

    public ProLoi stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMauSac() {
        return mauSac;
    }

    public ProLoi mauSac(String mauSac) {
        this.mauSac = mauSac;
        return this;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public Boolean isQc() {
        return qc;
    }

    public ProLoi qc(Boolean qc) {
        this.qc = qc;
        return this;
    }

    public void setQc(Boolean qc) {
        this.qc = qc;
    }

    public Boolean isQa() {
        return qa;
    }

    public ProLoi qa(Boolean qa) {
        this.qa = qa;
        return this;
    }

    public void setQa(Boolean qa) {
        this.qa = qa;
    }

    public Boolean isChecked() {
        return checked;
    }

    public ProLoi checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public ProLoi ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ProCumLoi getProCumLoi() {
        return proCumLoi;
    }

    public ProLoi proCumLoi(ProCumLoi proCumLoi) {
        this.proCumLoi = proCumLoi;
        return this;
    }

    public void setProCumLoi(ProCumLoi proCumLoi) {
        this.proCumLoi = proCumLoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProLoi)) {
            return false;
        }
        return id != null && id.equals(((ProLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProLoi{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", cusCode='" + getCusCode() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            ", label='" + getLabel() + "'" +
            ", stt=" + getStt() +
            ", mauSac='" + getMauSac() + "'" +
            ", qc='" + isQc() + "'" +
            ", qa='" + isQa() + "'" +
            ", checked='" + isChecked() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
