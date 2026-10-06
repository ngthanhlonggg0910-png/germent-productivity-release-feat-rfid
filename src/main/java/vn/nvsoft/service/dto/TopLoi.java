package vn.nvsoft.service.dto;

public class TopLoi {
    String caption;
    Integer soLuong;
    String mau;

    public TopLoi() {
    }

    public TopLoi(String caption, Integer soLuong, String mau) {
        this.caption = caption;
        this.soLuong = soLuong;
        this.mau = mau;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }
}
