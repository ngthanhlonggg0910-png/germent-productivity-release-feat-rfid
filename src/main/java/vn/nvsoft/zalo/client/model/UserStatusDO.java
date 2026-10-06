package vn.nvsoft.zalo.client.model;

public class UserStatusDO {

    private String statusName;

    private int quantity;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

}
