package javacources.api.repositories;

import javacources.entitiy.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface
GuestRepository extends CrudRepository<Guest, Long> {
    List<Guest> findAll();
}
