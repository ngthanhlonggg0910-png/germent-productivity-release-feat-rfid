package vn.nvsoft.zalo.client;

import vn.nvsoft.zalo.client.invoker.ZaloTokenInvoker;
import vn.nvsoft.zalo.client.invoker.ZaloLoginInvoker;
import vn.nvsoft.zalo.client.invoker.ZaloMessageInvoker;

/**
 * Presents all of services in which communicate to CSA systems.
 */
public interface CSAClient {

	ZaloLoginInvoker csaLoginService();

    ZaloTokenInvoker csaTokenService();

    ZaloMessageInvoker zaloMessageService();

    void close();

}
