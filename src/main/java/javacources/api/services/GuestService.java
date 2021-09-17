package javacources.api.services;

import javacources.entitiy.Guest;
import javacources.entitiy.Services;

import java.util.List;

public interface GuestService {
    void add(Guest guest);

    Guest get(Long guestId);

    List<Guest> getAll();

    void update(Guest guest);

    int getCount();
}
