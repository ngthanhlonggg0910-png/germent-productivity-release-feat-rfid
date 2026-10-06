package vn.nvsoft.zalo.client.model;

public class ZaloMessageResponseDO extends BasicDO{
    private ZaloMessageResponseDTO data;

    public ZaloMessageResponseDTO getData() {
        return data;
    }

    public void setData(ZaloMessageResponseDTO data) {
        this.data = data;
    }
}
