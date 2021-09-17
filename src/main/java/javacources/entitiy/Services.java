package javacources.entitiy;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Services extends BaseEntity {

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private Services services;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
