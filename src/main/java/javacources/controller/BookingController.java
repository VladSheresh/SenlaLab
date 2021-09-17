package javacources.controller;

import javacources.api.services.BookingService;
import javacources.api.services.GuestService;
import javacources.api.services.RoomService;
import javacources.entitiy.Booking;
import javacources.dto.GuestAndRoomDto;
import javacources.entitiy.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private GuestService guestService;

    public void addBooking(Long roomId, List<Long> guestsId, String dateCheckIn, String dateCheckOut){
        roomService.settleInRoom(roomId);
        Booking booking =  new Booking();
        booking.setRoom(roomService.get(roomId));
        guestsId.forEach(x -> booking.getGuests().add(guestService.get(x)));
        booking.setDateCheckIn(getDate(dateCheckIn));
        booking.setDateCheckOut(getDate(dateCheckOut));
        bookingService.add(booking);
    }

    public void getFreeRoomsByDate(String date){
        bookingService.getFreeListByDate(getDate(date));
    }

    public int getPriceRoomForGuest(int guestId){
        return bookingService.getPrice(guestId);
    }

    public List<Booking> getRoomGuestHistory(int roomId){
        return bookingService.getGuestHistory(roomId);
    }
    public Booking getRoomData(int roomId){
        return bookingService.getData(roomId);
    }

    public List<GuestAndRoomDto> getSortGuestAndRoom(){
        return bookingService.getSortGuestAndRoom();
    }

    public List<Services> getSortGuestServices(int guestId){
       return bookingService.getSortGuestServices(guestId);// спросить
    }

    public void updateStatusRoom(){
        bookingService.updateStatusRoom();
    }

    private LocalDate getDate(String s){
        String[] date = s.split(".");
        return LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }
}
