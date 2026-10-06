package vn.nvsoft.zalo.client.api.controller;

import io.swagger.annotations.ApiParam;
import vn.nvsoft.zalo.client.model.ZaloMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface ZaloMessageController. Used to send zalo message to user ID
 */
@Path("/v2.0/oa")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ZaloMessageController {

    /**
     * Send zalo message.
     *
     * @param zaloMessage: the message request {@link ZaloMessage}
     * @return the token response containing access_token and refresh_token
     *         {@link vn.nvsoft.zalo.client.model.ZaloMessageResponseDO}
     */
    @POST
    @Path("/message")
    Response sendMessage(@javax.ws.rs.HeaderParam("access_token") String accessToken, @ApiParam(value = "Send zalo message", required = true) ZaloMessage zaloMessage);


}
