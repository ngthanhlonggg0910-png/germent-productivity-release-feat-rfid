package vn.nvsoft.zalo.client.api;

import vn.nvsoft.zalo.client.configuration.CSAClientConfiguration;
import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.api.validator.ErrorCodeValidator;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractApi<T> implements Observer {

    private CSAApi apiClient;

    protected T proxy;

    private ErrorCodeValidator _validator;

    public AbstractApi() {
        this(CSAClientConfiguration.getDefaultApiClient());
    }

    public AbstractApi(CSAApi apiClient) {
        this.apiClient = apiClient;
        initProxy();
        _validator = new ErrorCodeValidator();
        //registerObserver();
    }

   /* private void registerObserver() {
        getApiClient().addObserver(this);
    }
*/
    protected abstract void initProxy();

    public CSAApi getApiClient() {
        return apiClient;
    }

    public void setApiClient(CSAApi apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void update(Observable o, Object arg) {
        // When a hostname got changed, re-init proxy to ensure the new client and target are updated.
        initProxy();
    }

    public ErrorCodeValidator getValidator() {
        return _validator;
    }

}
