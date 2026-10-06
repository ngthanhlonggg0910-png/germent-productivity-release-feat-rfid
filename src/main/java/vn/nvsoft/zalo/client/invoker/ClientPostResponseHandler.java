package vn.nvsoft.zalo.client.invoker;

import vn.nvsoft.zalo.client.model.BasicDO;

public interface ClientPostResponseHandler {

    /**
     * Do something after received the response
     * @param result The response
     * @param agrs List parameter pass from request
     * @param <T>
     */
    <T extends BasicDO> void handle(Result<T> result, Object... agrs);

}
