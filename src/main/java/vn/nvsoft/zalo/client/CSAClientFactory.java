package vn.nvsoft.zalo.client;

import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.invoker.ZaloTokenInvoker;
import vn.nvsoft.zalo.client.invoker.ZaloLoginInvoker;
import vn.nvsoft.zalo.client.invoker.ClientPostResponseHandler;
import vn.nvsoft.zalo.client.invoker.ZaloMessageInvoker;

import java.util.List;

/**
 * Presents a factory client implementation to initialize for lazy servers.
 */
public class CSAClientFactory implements CSAClient, AutoCloseable {

    private CSAApi _apiClient;
    private ZaloLoginInvoker _zaloLoginInvoker;
    private ZaloTokenInvoker _csaTokenService;
    private ZaloMessageInvoker _zaloMessageService;

    /**
     * Create a client factory on given csa host address.
     * @param host CSA Host address.
     * @param postResponseHandlers Do something after received the response if it is not null
     */
    public CSAClientFactory(String host, List<ClientPostResponseHandler> postResponseHandlers) {
        this._apiClient = new CSAApi(host, postResponseHandlers);
    }

    @Override
	public ZaloLoginInvoker csaLoginService() {
        if (_zaloLoginInvoker == null) {
            _zaloLoginInvoker = new ZaloLoginInvoker(this._apiClient);
        }
        return _zaloLoginInvoker;
    }

    @Override
    public ZaloTokenInvoker csaTokenService() {
        if (_csaTokenService == null) {
            _csaTokenService = new ZaloTokenInvoker(_apiClient);
        }
        return _csaTokenService;
    }

    @Override
    public ZaloMessageInvoker zaloMessageService() {
        if (_zaloMessageService == null) {
            _zaloMessageService = new ZaloMessageInvoker(_apiClient);
        }
        return _zaloMessageService;
    }

    @Override
    public void close() {
        _apiClient.close();
    }
}
