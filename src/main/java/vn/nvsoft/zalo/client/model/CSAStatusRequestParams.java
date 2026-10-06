package vn.nvsoft.zalo.client.model;

import java.util.Objects;

/**
 * The Class CSAStatusRequestParams holds parameters to call CSA to retrieve
 * Users or Video Devices resource is the flag that indicates if we are
 * retrieving Users (false) or Video Devices (true)
 */
public class CSAStatusRequestParams {

    private boolean resource;
    private boolean noChache;

    public CSAStatusRequestParams() {
        this(false, false);
    }

    public CSAStatusRequestParams(boolean resource) {
        this(resource, false);
    }

    public CSAStatusRequestParams(boolean resource, boolean noChache) {
        this.resource = resource;
        this.noChache = noChache;
    }

    public boolean isResource() {
        return resource;
    }

    public void setResource(boolean resource) {
        this.resource = resource;
    }

    public boolean isNoChache() {
        return noChache;
    }

    public void setNoChache(boolean noChache) {
        this.noChache = noChache;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CSAStatusRequestParams that = (CSAStatusRequestParams) o;
        return resource == that.resource && noChache == that.noChache;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource, noChache);
    }
}
