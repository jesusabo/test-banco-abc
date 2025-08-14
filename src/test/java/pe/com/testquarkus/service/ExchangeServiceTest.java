package pe.com.testquarkus.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import pe.com.testquarkus.dto.ExchangeAPIResponseDTO;
import pe.com.testquarkus.entity.Exchange;
import pe.com.testquarkus.exceptions.ResourceException;
import pe.com.testquarkus.mapper.ExchangeMapper;
import pe.com.testquarkus.proxy.ExchangeProxy;
import pe.com.testquarkus.repository.ExchangeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ExchangeServiceTest {

    @Inject
    ExchangeService service;

    @InjectMock
    ExchangeRepository repository;

    @InjectMock
    ExchangeMapper mapper;

    @InjectMock
    @RestClient
    ExchangeProxy exchangeProxy;

    @Inject
    Logger log;

    @Test
    @Transactional
    void testGetExchange_ok() {
        String dni = "12345678";

        when(repository.countByDNI(dni)).thenReturn(5L);

        ExchangeAPIResponseDTO apiResponse = new ExchangeAPIResponseDTO();
        when(exchangeProxy.getExchangeAPI()).thenReturn(apiResponse);

        Exchange exchangeEntity = new Exchange();
        when(mapper.ExchangeAPIToExchange(apiResponse, dni)).thenReturn(exchangeEntity);


        ExchangeAPIResponseDTO expectedDTO = new ExchangeAPIResponseDTO();
        when(mapper.ExchangeToExchangeAPIToExchange(exchangeEntity)).thenReturn(expectedDTO);

        ExchangeAPIResponseDTO result = service.getExchange(dni);

        assertNotNull(result);
        assertSame(expectedDTO, result);

        verify(repository).countByDNI(dni);
        verify(exchangeProxy).getExchangeAPI();
        verify(repository).persist(exchangeEntity);
    }

    @Test
    void testGetExchange_LimiteConsultas() {
        String dni = "87654321";

        when(repository.countByDNI(dni)).thenReturn(12L);

        ResourceException ex = assertThrows(ResourceException.class,
                () -> service.getExchange(dni));

        assertEquals("Supero el limite de consultas por dia", ex.getMessage());
        verifyNoInteractions(exchangeProxy);
    }
}