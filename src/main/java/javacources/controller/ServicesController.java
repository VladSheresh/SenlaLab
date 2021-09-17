package javacources.controller;

import javacources.entitiy.Services;
import javacources.api.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public String ping() {
        return "hello";
    }


    public void addServices(int price){
        Services service = new Services();
        service.setPrice(price);
        servicesService.add(service);
    }

    public void changePrice(Long serviceId, int price){

        servicesService.changePrice(serviceId, price);
    }

    public List<Services> getSortPriceServices(){
        return servicesService.getPriceSorted();
    }
}
