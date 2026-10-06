package vn.nvsoft.zalo.client.invoker;

import javax.ws.rs.core.Response;

import vn.nvsoft.zalo.client.model.BasicDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Presents the wrapper class {@link Result} from response results.
 */
public class Result<T extends BasicDO> {

    private T entity;
    private String error;
    private int errorCode;
    private Response.StatusType status;

    /**
     * Create new response result;
     */
    public Result() {
        super();
    }

    /**
     * Indicate whether response of request is getting error or not.
     * @return True if error message or status is not 200 OK, False or otherwise.
     */
    public boolean hasError() {
        return errorCode != 0 || !isOK();
    }

    /**
     * Indicate whether response result has response body and success.
     * @return True if response successfully and has response body.
     */
    public boolean hasEntityBody() {
        return hasNoError() && entity != null;
    }

    /**
     * Indicate whether response of request is successfully or not.
     * @return True if no error message or status is 200, False or otherwise.
     */
    public boolean hasNoError() {
        return !hasError();
    }

    /**
     * Indicate whether response is success with 200 OK status code.
     * @return True if response status code is 200, False for otherwise.
     */
    public boolean isOK() {
        return Response.Status.OK.equals(status);
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
        setErrorCode(entity.getError());
        setError(entity.getMessage());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Response.StatusType getStatus() {
        return status;
    }

    public void setStatus(Response.StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("entity", entity)
                .append("error", error)
                .append("errorCode", errorCode)
                .append("status", status)
                .toString();
    }
}
