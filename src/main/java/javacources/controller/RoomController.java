package javacources.controller;

import javacources.api.services.RoomService;
import javacources.entitiy.Room;
import javacources.entitiy.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    public void addRoom(int price, int capacity, int countStars){
        Room room = new Room();
        room.setPrice(price);
        room.setCapacity(capacity);
        room.setCountStars(countStars);
        roomService.add(room);
    }

    public void changePrice(Long roomId, int price){
        roomService.changePrice(roomId, price);
    }

    public void changeStatus(Long roomId, Status status){
        roomService.changeStatus(roomId, status);
    }

    public void settleInRoom(Long roomId){
        roomService.settleInRoom(roomId);
    }

    public void checkOutTheRoom(Long roomId){
        roomService.checkOutTheRoom(roomId);
    }

    public int getCountFreeRooms(){
       return roomService.getFreeCount();
    }

    public List<Room> getSortRooms(){
        return roomService.getAllSorted();
    }

    @GetMapping
    public List<Room> getSortFreeRooms(){
        return roomService.getFreeSorted();
    }

    public List<Room> getSortPriceRooms(){
        return roomService.getPriceSorted();
    }
}
