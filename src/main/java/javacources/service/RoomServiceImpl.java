package javacources.service;

import javacources.api.repositories.RoomRepository;
import javacources.api.services.RoomService;
import javacources.entitiy.Room;
import javacources.entitiy.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void add(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Room get(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public void update(Room room) {
        int index = roomRepository.findAll().indexOf(room);
        roomRepository.findAll().set(index, room);
    }

    @Override
    public void changePrice(Long roomId, int price) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setPrice(price);
        update(room);
    }

    @Override
    public int getFreeCount() {
        return roomRepository.findAll().size();
    }

    @Override
    public void changeStatus(Long roomId, Status status) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (room.getStatus() != Status.BUSY) {
            room.setStatus(status);
        } else {
            System.out.println("Этот номер занят!!!");
        }
    }

    @Override
    public void settleInRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (room.getStatus() == Status.FREE) {
            room.setStatus(Status.BUSY);
        } else {
            System.out.println("Поселить в этот номер невозможно!!!");
        }
    }

    @Override
    public void checkOutTheRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (room.getStatus() == Status.BUSY) {
            room.setStatus(Status.FREE);
        } else {
            System.out.println("Этот номер и так свобожен!!!");

        }
    }

    @Override
    public List<Room> getAllSorted() {
        return roomRepository.findAllByOrderByPriceAscCapacityAscCountStarsAsc();
    }

    @Override
    @Transactional
    public List<Room> getFreeSorted() {
        List<Room> rooms = roomRepository.findAllByStatusOrderByPriceAscCapacityAscCountStarsAsc(Status.FREE);
        return rooms;
    }

    @Override
    public List<Room> getPriceSorted() {
        return roomRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Room> getFreeLis() {
        return roomRepository.findAllByStatus(Status.FREE);
    }
}



