package vn.nvsoft.zalo.client.invoker;

import javax.ws.rs.core.Response;
import java.util.function.Supplier;

import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.model.BasicDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


/**
 * Presents the basic service to invoke to external system via services.
 */
public abstract class AbstractServiceInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceInvoker.class.getName());

    private CSAApi _csaApi;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     * Create basic invoker service on given client details.
     * @param csaApi : client details.
     */
    public AbstractServiceInvoker(CSAApi csaApi) {
        this._csaApi = csaApi;
    }

    /**
     * To create or initialize a proxy service class.
     * @param controller : a proxy service class as interface.
     * @return An instance of proxy service.
     */
    protected <T> T wrap(Class<T> controller) {
        return getWebTarget().proxy(controller);
    }

    /**
     * Helper to get web client target.
     * @return {@link ResteasyWebTarget}.
     */
    protected ResteasyWebTarget getWebTarget() {
        return _csaApi.getWebTarget();
    }

    /**
     * As factory method to handle calling/invoke operations of proxy services and convert to result of response.
     * @param invoker Operation of proxy services.
     * @param entity Entity values response from results of invoker.
     * @param agrs The parameter will pass to {@Link ClientPostResponseHandlers}
     * @return Wrapper response of invoker to result object {@link Result}.
     */
     protected  <T extends BasicDO> Result<T> callRequestForObject(Supplier<Response> invoker, Class<T> entity, Object... agrs) {
        Result<T> result = new Result<>();
        Response response = null;
        try {
            response = invoker.get();
            result.setStatus(response.getStatusInfo());
            String body = response.readEntity(String.class);
            if (body != null && !body.isEmpty()) {
                result.setEntity(OBJECT_MAPPER.readValue(body, entity));
            }
        } catch (Exception exception) {
            LOGGER.error("Error callRequest ", exception);
            //Trace unexpected failure, maybe wrong host name or communicate linked failure.
            result.setError(exception.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
         if (_csaApi.getClientPostResponseHandlers() != null) {
             _csaApi.getClientPostResponseHandlers().forEach(postResponseHandler -> {
                 postResponseHandler.handle(result, agrs);
             });

         }
        return result;
    }

    public CSAApi getCsaApi() {
        return _csaApi;
    }

    public void setCsaApi(CSAApi csaApi) {
        this._csaApi = csaApi;
    }
}
