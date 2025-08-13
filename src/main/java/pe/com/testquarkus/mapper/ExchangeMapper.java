package pe.com.testquarkus.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.com.testquarkus.dto.ExchangeAPIResponseDTO;
import pe.com.testquarkus.entity.Exchange;

@Mapper(componentModel = "cdi")
public interface ExchangeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dni", expression = "java(dni)")
    Exchange ExchangeAPIToExchange(ExchangeAPIResponseDTO exchangeAPIResponse, @Context String dni);


    ExchangeAPIResponseDTO ExchangeToExchangeAPIToExchange(Exchange exchange);
}
