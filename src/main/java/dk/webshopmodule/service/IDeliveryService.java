package dk.webshopmodule.service;

import dk.webshopmodule.model.Delivery;

import java.util.List;

public interface IDeliveryService {
    void createDelivery(Delivery delivery);
    List<Delivery> fetchAllDelivery();
    Delivery fetchOneDelivery(int id);
    void updateDelivery(Delivery delivery);
    void deleteDelivery(int id);
}
