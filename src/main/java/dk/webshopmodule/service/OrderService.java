package dk.webshopmodule.service;

import dk.webshopmodule.model.Customer;
import dk.webshopmodule.model.Order;
import dk.webshopmodule.repository.ICustomerRepo;
import dk.webshopmodule.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    ICustomerRepo customerRepo;

    @Override
    public int addOrder(Order order) {
        Order order1 = orderRepo.save(order);
        return order1.getId();
    }
}
