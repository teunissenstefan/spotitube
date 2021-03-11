package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    @Inject
    public UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersAsJson(@QueryParam("token") String token) {
        userService.getUserByToken(token);
        return Response.ok(userService.getAllUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id, @QueryParam("token") String token) {
        userService.getUserByToken(token);
        return Response.ok(userService.getUserById(id)).build();
    }
}
