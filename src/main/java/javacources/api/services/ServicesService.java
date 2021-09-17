package javacources.api.services;

import javacources.entitiy.Booking;
import javacources.entitiy.Services;

import java.util.List;

public interface ServicesService {
    void add(Services services);

    Services get(Long servicesId);

    List<Services> getAll();

    void update(Services services);

    void changePrice(Long servicesId, int price);

    List<Services> getPriceSorted();
}
