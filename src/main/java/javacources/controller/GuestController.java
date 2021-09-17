package javacources.controller;

import javacources.api.services.GuestService;
import javacources.api.services.ServicesService;
import javacources.entitiy.Guest;
import javacources.entitiy.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @Autowired
    private ServicesService servicesService;

    public void addGuest(String name, List<Long> serviceGuest){
        Guest guest = new Guest();
        guest.setName(name);
        guest.setServiceGuest(getServicesGuest(serviceGuest));
        guestService.add(guest);
    }

    public int getCountGuest(){
        return guestService.getCount();
    }

    private  List<Services> getServicesGuest(List<Long> list){
        List<Services> servicesGuest = new ArrayList<>();
        list.forEach(x -> servicesGuest.add(servicesService.get(x)));
        return servicesGuest;
    }
}
