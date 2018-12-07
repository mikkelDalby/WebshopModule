package dk.webshopmodule.controller;

import dk.webshopmodule.model.Product;
import dk.webshopmodule.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    IProductService productService;

    @GetMapping("")
    public String admin(HttpServletRequest request, @ModelAttribute Product product, Model model){
        if (request.isUserInRole("ADMIN")){
            model.addAttribute("products", productService.getAllProducts());
            return "admin/index";
        }
        return "redirect:/redirect";
    }

    @GetMapping("/createProduct")
    public String createProducts(){

        return "/admin/createProduct";
    }

    @PostMapping("/createProduct")
    public String createProducts(@ModelAttribute Product product){
        productService.createProduct(product);

        return "redirect:/admin/createProduct";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateStudent(@PathVariable String id,@ModelAttribute Product product, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return ("/admin/updateProduct");

    }

    @PostMapping("/updateProduct")
    public String update(@ModelAttribute Product product){
        productService.updateProduct(product);
        return "redirect:/admin";

    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);

        return "redirect:/admin";
    }
}