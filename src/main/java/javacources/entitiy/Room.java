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
public class Room extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer price;
    private Integer capacity;
    private Integer countStars;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @Override
    public String toString() {
        return String.format("цена: %d \tвместимость: %d \tколичество звёзд: %d\n ",
                price, capacity, countStars);
    }

}
