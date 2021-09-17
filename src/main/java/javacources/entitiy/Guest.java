package javacources.entitiy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Guest extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "services")
    private List<Services> serviceGuest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
