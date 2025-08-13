package pe.com.testquarkus.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<ResourceException> {

    @Override
    public Response toResponse(ResourceException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.builder().error(ex.getMessage()).build()).build();
    }

    @Builder
    @AllArgsConstructor
    public static class ErrorResponse {
        public String error;
    }
}
