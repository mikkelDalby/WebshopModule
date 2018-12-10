package dk.webshopmodule.service;

import dk.webshopmodule.model.Delivery;
import dk.webshopmodule.repository.IDeliveryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryImpl implements IDeliveryService {
    @Autowired
    IDeliveryRepo deliveryRepo;

    @Override
    public void createDelivery(Delivery delivery) {
        deliveryRepo.save(delivery);
    }

    @Override
    public List<Delivery> fetchAllDelivery() {
        return deliveryRepo.findAll();
    }

    @Override
    public Delivery fetchOneDelivery(int id) {
        return deliveryRepo.getOne(id);
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        deliveryRepo.save(delivery);
    }

    @Override
    public void deleteDelivery(int id) {
        deliveryRepo.deleteById(id);
    }
}