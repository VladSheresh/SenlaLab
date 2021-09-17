package javacources.service;

import javacources.api.services.ServicesService;
import javacources.entitiy.Services;
import javacources.api.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {

    @Autowired
   private ServicesRepository servicesRepository;


    @Override
    public void changePrice(Long serviceId, int price) {
        Services service = servicesRepository.findById(serviceId).orElseThrow();
        service.setPrice(price);
        update(service);
    }

    @Override
    public List<Services> getPriceSorted() {
        return servicesRepository.findAllByOrderByPrice();
    }

    @Override
    public void add(Services services) {
        servicesRepository.save(services);
    }

    @Override
    public Services get(Long serviceId) {
        return servicesRepository.findById(serviceId).orElseThrow();
    }

    @Override
    public List<Services> getAll() {
        return servicesRepository.findAll();
    }

    @Override
    public void update(Services services) {
        int index = servicesRepository.findAll().indexOf(services);
        servicesRepository.findAll().set(index, services);
    }
}
