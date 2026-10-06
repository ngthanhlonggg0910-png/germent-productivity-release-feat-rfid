package vn.nvsoft.zalo.client.api.controller;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.ApiParam;

/**
 * Interface CSATokenController. Used to handling OAuth2 token handling and
 * request admin details.
 */
@Path("/v4/oa")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ZaloTokenController {

//    @POST
//    @Path("/access_token")
//    Response accessToken(
//        @javax.ws.rs.HeaderParam("secret_key") String secretKey,
//            @ApiParam(value = "Request access token parameters", required = true) Entity<Form> entity);

    @POST
    @Path("/access_token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response accessToken(
        @HeaderParam("secret_key") String secretKey,
        @FormParam("refresh_token") String refreshToken,
        @FormParam("app_id") String appId,
        @FormParam("grant_type") String grantType);


}
