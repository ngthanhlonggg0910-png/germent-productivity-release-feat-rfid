package vn.nvsoft.zalo.client.configuration;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import vn.nvsoft.zalo.client.invoker.ClientPostResponseHandler;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

/**
 * Presents the model presentation of CSA details requests to communicate to external CSA services.
 */
public class CSAApi {

    private static final String CSA_REST_ROOT_PATH_PATTERN = "https://%s";
    public static final String CSA_DEFAULT_HOST = "openapi.zaloapp.com";

    protected String host;
    protected String csaRestRootPath;
    protected ResteasyWebTarget webTarget;
    private List<ClientPostResponseHandler> _clientPostResponseHandlers = null;
    /**
     * To create a CSA API on default CSA host address and configuration.
     */
    public CSAApi() {
        this(CSA_DEFAULT_HOST, null);
    }

    /**
     *  Create a CSA API with default configurations and on given CSA Host address.
     * @param host : CSA Host Address.
     */
    public CSAApi(@NotNull String host, List<ClientPostResponseHandler> postResponseHandlers) {
        setHost(host);
        setCsaRestRootPath(String.format(CSA_REST_ROOT_PATH_PATTERN, host));
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        client.register(JacksonJaxbJsonProvider.class);
        webTarget = client
                .target(UriBuilder.fromPath(csaRestRootPath));
        _clientPostResponseHandlers = postResponseHandlers;
    }

    /**
     * To close the client connection.
     */
    public void close() {
        if (webTarget != null) {
            try {
                webTarget.getResteasyClient().close();
            } catch (Throwable e) {
                //Unexpected error during close.
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ResteasyWebTarget getWebTarget() {
        return webTarget;
    }

    public void setWebTarget(ResteasyWebTarget webTarget) {
        this.webTarget = webTarget;
    }

    public String getCsaRestRootPath() {
        return csaRestRootPath;
    }

    public void setCsaRestRootPath(String csaRestRootPath) {
        this.csaRestRootPath = csaRestRootPath;
    }

    public List<ClientPostResponseHandler> getClientPostResponseHandlers() {
        return _clientPostResponseHandlers;
    }

    public void setClientPostResponseHandlers(List<ClientPostResponseHandler> clientPostResponseHandlers) {
        _clientPostResponseHandlers = clientPostResponseHandlers;
    }
}
