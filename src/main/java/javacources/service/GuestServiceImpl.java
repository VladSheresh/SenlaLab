package javacources.service;

import javacources.api.repositories.GuestRepository;
import javacources.api.services.GuestService;
import javacources.entitiy.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public void add(Guest guest) {
        guestRepository.save(guest);
    }

    @Override
    public Guest get(Long guestId) {
        return guestRepository.findById(guestId).orElseThrow();
    }

    @Override
    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    @Override
    public void update(Guest guest) {
        int index = guestRepository.findAll().indexOf(guest);
        guestRepository.findAll().set(index, guest);
    }

    @Override
    public int getCount() {
        return guestRepository.findAll().size();
    }
}
