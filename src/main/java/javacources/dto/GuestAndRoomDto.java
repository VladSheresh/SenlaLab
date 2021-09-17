package javacources.dto;

import javacources.entitiy.Guest;
import javacources.entitiy.Room;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestAndRoomDto {

    private Guest guest;
    private Room room;
    private LocalDate dateCheckOut;

}
