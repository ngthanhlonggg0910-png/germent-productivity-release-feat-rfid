package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.GopYChatLuong} entity.
 */
public class GopYChatLuongDTO implements Serializable {

    private Long id;

    private Integer cusId;

    private String cusName;

    private Integer chuyenId;

    private String chuyenName;

    private String maHang;

    private String po;

    private Integer poId;

    private String color;

    private String size;

    private Boolean firstCut;

    private Boolean inline;

    private Integer lanBanHanh;

    private Instant ngayBaoCao;

    private String nguoiTao;

    private Instant userId;

    private Instant ngayTao;

    private String tenQuanLyXuong;

    private String tenKiThuat;

    private String tenQanLyCat;

    private String tenQA;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getChuyenId() {
        return chuyenId;
    }

    public void setChuyenId(Integer chuyenId) {
        this.chuyenId = chuyenId;
    }

    public String getChuyenName() {
        return chuyenName;
    }

    public void setChuyenName(String chuyenName) {
        this.chuyenName = chuyenName;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean isFirstCut() {
        return firstCut;
    }

    public void setFirstCut(Boolean firstCut) {
        this.firstCut = firstCut;
    }

    public Boolean isInline() {
        return inline;
    }

    public void setInline(Boolean inline) {
        this.inline = inline;
    }

    public Integer getLanBanHanh() {
        return lanBanHanh;
    }

    public void setLanBanHanh(Integer lanBanHanh) {
        this.lanBanHanh = lanBanHanh;
    }

    public Instant getNgayBaoCao() {
        return ngayBaoCao;
    }

    public void setNgayBaoCao(Instant ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getUserId() {
        return userId;
    }

    public void setUserId(Instant userId) {
        this.userId = userId;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenQuanLyXuong() {
        return tenQuanLyXuong;
    }

    public void setTenQuanLyXuong(String tenQuanLyXuong) {
        this.tenQuanLyXuong = tenQuanLyXuong;
    }

    public String getTenKiThuat() {
        return tenKiThuat;
    }

    public void setTenKiThuat(String tenKiThuat) {
        this.tenKiThuat = tenKiThuat;
    }

    public String getTenQanLyCat() {
        return tenQanLyCat;
    }

    public void setTenQanLyCat(String tenQanLyCat) {
        this.tenQanLyCat = tenQanLyCat;
    }

    public String getTenQA() {
        return tenQA;
    }

    public void setTenQA(String tenQA) {
        this.tenQA = tenQA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GopYChatLuongDTO gopYChatLuongDTO = (GopYChatLuongDTO) o;
        if (gopYChatLuongDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gopYChatLuongDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GopYChatLuongDTO{" +
            "id=" + getId() +
            ", cusId=" + getCusId() +
            ", cusName='" + getCusName() + "'" +
            ", chuyenId=" + getChuyenId() +
            ", chuyenName='" + getChuyenName() + "'" +
            ", maHang='" + getMaHang() + "'" +
            ", po='" + getPo() + "'" +
            ", poId=" + getPoId() +
            ", color='" + getColor() + "'" +
            ", size='" + getSize() + "'" +
            ", firstCut='" + isFirstCut() + "'" +
            ", inline='" + isInline() + "'" +
            ", lanBanHanh=" + getLanBanHanh() +
            ", ngayBaoCao='" + getNgayBaoCao() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", userId='" + getUserId() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", tenQuanLyXuong='" + getTenQuanLyXuong() + "'" +
            ", tenKiThuat='" + getTenKiThuat() + "'" +
            ", tenQanLyCat='" + getTenQanLyCat() + "'" +
            ", tenQA='" + getTenQA() + "'" +
            "}";
    }
}
