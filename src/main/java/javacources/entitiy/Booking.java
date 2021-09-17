package javacources.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Booking extends BaseEntity{

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    private List<Guest> guests;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_fk")
    private Room room;

    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
}
