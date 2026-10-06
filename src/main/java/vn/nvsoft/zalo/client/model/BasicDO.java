package vn.nvsoft.zalo.client.model;

/**
 * Presents basic model DO for response from CSA system.
 */
public class BasicDO implements IErrorCode {

    private int error;
    private String message;

    /**
     * To create new BasicDO instance.
     */
    public BasicDO() {
        super();
    }

    /**
     * Indicate whether the response is successfully with response body or not.
     * @return True if response body valid, False for otherwise.
     */
    public boolean isSuccess() {
        return error == 0;
    }

    public void setError(int errorCode) {
        this.error = errorCode;
    }

    @Override
    public int getError() {
        return error;
    }

    public void setMessage(String error) {
        this.message = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
