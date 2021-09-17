package javacources.api.services;

import javacources.entitiy.Guest;
import javacources.entitiy.Room;
import javacources.entitiy.Status;

import java.util.List;

public interface RoomService {
    void add(Room room);

    Room get(Long roomId);

    List<Room> getAll();

    void update(Room room);

    void changeStatus(Long roomId, Status status);

    void settleInRoom(Long roomId);

    void checkOutTheRoom(Long roomId);

    void changePrice(Long roomId, int price);

    int getFreeCount();

    List<Room> getAllSorted();

    List<Room> getFreeSorted();

    List<Room> getPriceSorted();

    List<Room> getFreeLis();
}
