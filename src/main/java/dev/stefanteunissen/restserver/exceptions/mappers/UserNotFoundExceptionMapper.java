package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.RestErrorDTO;
import dev.stefanteunissen.restserver.exceptions.UserNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(new RestErrorDTO(1, e.getMessage())).build();
    }
}
