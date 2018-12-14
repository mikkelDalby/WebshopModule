package dk.webshopmodule.service;

import dk.webshopmodule.model.OrderLine;
import dk.webshopmodule.repository.IOrderlineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderlineService implements IOrderlineService {

    @Autowired
    IOrderlineRepo orderlineRepo;

    @Override
    public void addOrderlines(List<OrderLine> orderLines) {
        orderlineRepo.saveAll(orderLines);
    }
}
