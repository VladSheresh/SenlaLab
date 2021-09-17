package javacources.api.services;

import javacources.dto.GuestAndRoomDto;
import javacources.entitiy.*;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void add(Booking booking);

    Booking get(Long bookingId);

    List<Booking> getAll();

    void update(Booking booking);

    List<GuestAndRoomDto> getSortGuestAndRoom();

    List<Services> getSortGuestServices(int guestId);

    List<Room> getFreeListByDate(LocalDate date);

    int getPrice(int guestId);

    List<Booking> getGuestHistory(int roomId);

    Booking getData(int roomId);

    void updateStatusRoom();
}
