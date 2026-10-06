package vn.nvsoft.zalo.client.api.exception;

public class CSAInValidTokenException extends RuntimeException{

    public CSAInValidTokenException() {
    }

    public CSAInValidTokenException(String message) {
        super(message);
    }
}
