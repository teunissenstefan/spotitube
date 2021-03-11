package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostLoginRequest;
import dev.stefanteunissen.restserver.services.LoginService;
import dev.stefanteunissen.restserver.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/login")
public class LoginResource {
    @Inject
    public LoginService loginService;
    @Inject
    public TokenService tokenService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(final PostLoginRequest request) {
        User user = loginService.login(request.user, request.password);
        Map<String, String> map = new HashMap<>();
        map.put("token", tokenService.setToken(user.getId()));
        map.put("user", request.user);

        return Response.created(URI.create("")).entity(map).build();
    }
}
