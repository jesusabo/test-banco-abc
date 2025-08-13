package pe.com.testquarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeAPIResponseDTO {

    public LocalDate fecha;
        public double sunat;
        public double compra;
        public double venta;

    }
