package dk.webshopmodule.service;

import dk.webshopmodule.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String id);

}