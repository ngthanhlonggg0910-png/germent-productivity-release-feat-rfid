package vn.nvsoft.zalo.client.configuration;

public class CSAClientConfiguration {
    private static CSAApi defaultApiClient = new CSAApi();

    /**
     * Get the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @return Default API client
     */
    public static CSAApi getDefaultApiClient() {
        return defaultApiClient;
    }

    /**
     * Set the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @param apiClient API client
     */
    public static void setDefaultApiClient(CSAApi apiClient) {
        defaultApiClient = apiClient;
    }
}
