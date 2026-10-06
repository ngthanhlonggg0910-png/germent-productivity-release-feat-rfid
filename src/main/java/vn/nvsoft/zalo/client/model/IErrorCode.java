package vn.nvsoft.zalo.client.model;

public interface IErrorCode {
    public static final int TOKEN_INVALID = -48;

    int getError();
    String getMessage();
}
