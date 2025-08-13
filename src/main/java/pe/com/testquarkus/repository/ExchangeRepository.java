package pe.com.testquarkus.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.com.testquarkus.entity.Exchange;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class ExchangeRepository implements PanacheRepository<Exchange> {

    public long countByDNI(String dni){
        LocalDate startDay = LocalDate.now();

        return count("dni = ?1 AND fecha = ?2",
                dni, startDay);
    }
}
