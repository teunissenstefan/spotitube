package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.RestErrorDTO;
import dev.stefanteunissen.restserver.exceptions.UserIncorrectDetailsEnteredException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserIncorrectDetailsEnteredExceptionMapper implements ExceptionMapper<UserIncorrectDetailsEnteredException> {
    @Override
    public Response toResponse(UserIncorrectDetailsEnteredException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new RestErrorDTO(2, e.getMessage())).build();
    }
}
