package dk.webshopmodule.service;

import dk.webshopmodule.model.OrderLine;

import java.util.List;

public interface IOrderlineService {
    void addOrderlines(List<OrderLine> orderLines);
}
