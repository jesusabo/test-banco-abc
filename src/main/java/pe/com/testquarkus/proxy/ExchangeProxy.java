package pe.com.testquarkus.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.com.testquarkus.dto.ExchangeAPIResponseDTO;

@Path("/")
@RegisterRestClient(configKey = "tipo-cambio-api")
public interface ExchangeProxy {

    @GET
    ExchangeAPIResponseDTO getExchangeAPI();
}
