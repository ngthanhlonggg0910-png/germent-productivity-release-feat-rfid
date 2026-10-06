package vn.nvsoft.zalo.client.api.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces({ MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public interface ZaloLoginController {

    /**
     * CSA Sign in. Deprecated. Check {@link ZaloTokenController}.
     *
     */
    @Deprecated
    @GET
    @Path("/login")
    Response login(@QueryParam("email") String email, @QueryParam("password") String password,
                          @QueryParam("web") @DefaultValue("true") boolean web,
                          @QueryParam("convergence") @DefaultValue("true") boolean convergence);

    /**
     * CSA Sign out.Deprecated. Check {@link ZaloTokenController}
     *
     */
    @Deprecated
    @DELETE
    @Path("/logout")
    Response logout(@QueryParam("email") String email, @QueryParam("token") String token,
                           @QueryParam("web") @DefaultValue("true") boolean web,
                           @QueryParam("convergence") @DefaultValue("true") boolean convergence);

}
