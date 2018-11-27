package dk.webshopmodule.service;

import dk.webshopmodule.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
}