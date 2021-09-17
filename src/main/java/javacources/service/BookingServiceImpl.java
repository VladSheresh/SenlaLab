package javacources.service;

import javacources.api.repositories.BookingRepository;
import javacources.api.services.BookingService;
import javacources.api.services.RoomService;
import javacources.dto.GuestAndRoomDto;
import javacources.entitiy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomService roomService;

    @Override
    public void add(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking get(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public void update(Booking booking) {
        int index = bookingRepository.findAll().indexOf(booking);
        bookingRepository.findAll().set(index, booking);
    }

    @Override
    public List<GuestAndRoomDto> getSortGuestAndRoom() {
        List<GuestAndRoomDto> sortGuestsAndRooms = new ArrayList<>();

        for (Booking booking : bookingRepository.findAll()) {
            for (Guest guest : booking.getGuests()){
                GuestAndRoomDto guestAndRoomDto = new GuestAndRoomDto();
                guestAndRoomDto.setGuest(guest);
                guestAndRoomDto.setRoom(booking.getRoom());
                guestAndRoomDto.setDateCheckOut(booking.getDateCheckOut());
                sortGuestsAndRooms.add(guestAndRoomDto);
            }
        }
        Comparator<GuestAndRoomDto> nameComparator = Comparator.comparing(o -> o.getGuest().getName());
        Comparator<GuestAndRoomDto> dateComparator = (o1, o2) -> {
            return o1.getDateCheckOut().isAfter(o2.getDateCheckOut()) ? 1 : o1.getDateCheckOut().isEqual(o2.getDateCheckOut()) ? 0 : -1;
        };
        Comparator<GuestAndRoomDto> guestOrRoomComparator = nameComparator
                .thenComparing(dateComparator);
        return sortGuestsAndRooms.stream().sorted(guestOrRoomComparator).collect(Collectors.toList());
    }

    @Override
    public List<Services> getSortGuestServices(int guestId) {
        //for (Booking booking : repository.getAll()) {
        //    for (Map.Entry<Guest, List<Services>> entry : booking.getServiceGuest().entrySet()) {
        //        if(entry.getKey().getId() == guestId){
        //         return entry.getValue().stream().sorted(Comparator.comparingInt(Services::getPrice)).collect(Collectors.toList());
        //        }
        //   }
        //}
        return null;
    }

    @Override
    public List<Room> getFreeListByDate(LocalDate date) {
        List<Room> bookings = bookingRepository.findAll().stream()
                .filter(x ->  x.getDateCheckIn().isBefore(date) || x.getDateCheckIn().isEqual(date) && x.getDateCheckOut().isAfter(date) || x.getDateCheckOut().isEqual(date))
                .map(Booking::getRoom)
                .collect(Collectors.toList());
        List<Room> freeRooms = roomService.getFreeLis();
        freeRooms.removeAll(bookings);// удаляем занятые комнаты
        return freeRooms;
    }

    @Override
    public int getPrice(int guestId) {
        for (Booking booking : bookingRepository.findAll()) {
            for (Guest guest : booking.getGuests()) {
                if (guest.getId() == guestId) {
                    return booking.getRoom().getPrice();
                }
            }
        }
        //bookingRepository.getAll().forEach(x -> x.getGuests().forEach(y -> y.getId() == guestId?  return ));
        return 0;
    }

    @Override
    public List<Booking> getGuestHistory(int roomId) {
        List<Booking> bookings = bookingRepository.findAll().stream().filter(x -> x.getRoom().getId() == roomId).collect(Collectors.toList());
        Comparator<Booking> dateComparator = (o1, o2) -> {
            return o1.getDateCheckOut().isAfter(o2.getDateCheckOut()) ? 1 : o1.getDateCheckOut().isEqual(o2.getDateCheckOut()) ? 0 : -1;
        };
        bookings = bookings.stream().sorted(dateComparator).collect(Collectors.toList());
        if (bookings.size() > 2) {
            bookings.subList(0, 2);
        }
        return bookings;
    }

    @Override
    public Booking getData(int roomId) {
        List<Booking> bookings =bookingRepository.findAll().stream().filter(x -> x.getRoom().getId() == roomId).collect(Collectors.toList());
        Comparator<Booking> dateComparator = (o1, o2) -> {
            return o1.getDateCheckOut().isAfter(o2.getDateCheckOut()) ? 1 : o1.getDateCheckOut().isEqual(o2.getDateCheckOut()) ? 0 : -1;
        };
        bookings = bookings.stream().sorted(dateComparator).collect(Collectors.toList());
        return bookings.get(0);
    }

    @Override
    public void updateStatusRoom() {
        for (Booking booking :bookingRepository.findAll()) {
            if(booking.getDateCheckOut().isAfter(LocalDate.now())){
                booking.getRoom().setStatus(Status.FREE);
            }
        }
    }
}
