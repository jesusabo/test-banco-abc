package pe.com.testquarkus;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import pe.com.testquarkus.exceptions.ResourceException;
import pe.com.testquarkus.service.ExchangeService;


@Path("/tipo-cambio")
public class ExchangeResource {

    @Inject
    ExchangeService service;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{dni:.*}")
    public Response exchange(@PathParam("dni") @NotBlank String dni) {
        logger.infov("[Numero de dni: {0}]", dni);
        if (dni == null || dni.length() < 8 || dni.trim().isEmpty()) {
            throw new ResourceException("El DNI debe tener 8 caracteres");
        }
        return  Response.status(Response.Status.OK).entity(service.getExchange(dni)).build();
    }

}
