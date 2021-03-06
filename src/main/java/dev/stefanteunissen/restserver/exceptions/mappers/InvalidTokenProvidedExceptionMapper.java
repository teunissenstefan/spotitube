package dev.stefanteunissen.restserver.exceptions.mappers;

import dev.stefanteunissen.restserver.RestErrorDTO;
import dev.stefanteunissen.restserver.exceptions.InvalidTokenProvidedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTokenProvidedExceptionMapper implements ExceptionMapper<InvalidTokenProvidedException> {
    @Override
    public Response toResponse(InvalidTokenProvidedException e) {
        return Response.status(Response.Status.FORBIDDEN).entity(new RestErrorDTO(3, e.getMessage())).build();
    }
}
