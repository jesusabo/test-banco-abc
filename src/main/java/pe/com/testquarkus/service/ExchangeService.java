package pe.com.testquarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import pe.com.testquarkus.dto.ExchangeAPIResponseDTO;
import pe.com.testquarkus.exceptions.ResourceException;
import pe.com.testquarkus.mapper.ExchangeMapper;
import pe.com.testquarkus.proxy.ExchangeProxy;
import pe.com.testquarkus.entity.Exchange;
import pe.com.testquarkus.repository.ExchangeRepository;


@ApplicationScoped
public class ExchangeService {

    @Inject
    Logger log;

    @Inject
    ExchangeRepository repository;

    @Inject
    ExchangeMapper mapper;

    @Inject
    @RestClient
    ExchangeProxy exchangeProxy;

    @Transactional
    public ExchangeAPIResponseDTO getExchange(String dni) {
        log.infov("[getExchange dni: {0}]", dni);
        long registersForDNI = repository.countByDNI(dni);

        if(registersForDNI >= 10) {
            throw new ResourceException("Supero el limite de consultas por dia");
        }

        ExchangeAPIResponseDTO apiResponse = exchangeProxy.getExchangeAPI();
        if(apiResponse != null) {
            log.info("[Consulta a servicio Externo OK]");
        }
        Exchange exchange = mapper.ExchangeAPIToExchange(apiResponse, dni);
        repository.persist(exchange);
        log.info("[Tipo Cambio se guardo en DB]");
        return mapper.ExchangeToExchangeAPIToExchange(exchange);

    }
}
