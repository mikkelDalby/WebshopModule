package dk.webshopmodule.controller;

import dk.webshopmodule.model.Product;
import dk.webshopmodule.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IProductService productService;

    @GetMapping("/")
    public String index(){
        List<Product> products = productService.getAllProducts();
        return "webshop/index";
    }
}
