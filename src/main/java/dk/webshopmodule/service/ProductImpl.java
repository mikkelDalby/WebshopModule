package dk.webshopmodule.service;

import dk.webshopmodule.model.Product;
import dk.webshopmodule.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements IProductService{
    @Autowired
    IProductRepo productRepo;


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepo.getOne(id);
    }

    @Override
    public void createProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}