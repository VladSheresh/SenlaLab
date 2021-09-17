package javacources.api.repositories;

import javacources.entitiy.Room;
import javacources.entitiy.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    long count();
    List<Room> findAll();
    List<Room> findAllByOrderByPriceAscCapacityAscCountStarsAsc();
    List<Room> findAllByStatusOrderByPriceAscCapacityAscCountStarsAsc(Status status);
    List<Room> findAllByOrderByPriceAsc();
    List<Room> findAllByStatus(Status status);
}
