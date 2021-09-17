package javacources.api.repositories;

import javacources.entitiy.Services;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesRepository extends CrudRepository<Services, Long> {
    List<Services> findAll();

    List<Services> findAllByOrderByPrice();
}
