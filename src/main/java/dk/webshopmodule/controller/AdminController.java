package dk.webshopmodule.controller;

import dk.webshopmodule.model.Delivery;
import dk.webshopmodule.model.Payment;
import dk.webshopmodule.model.Product;
import dk.webshopmodule.service.IDeliveryService;
import dk.webshopmodule.service.IPaymentService;
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

    @Autowired
    IDeliveryService deliveryService;

    @Autowired
    IPaymentService paymentService;

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

    @GetMapping("/delivery")
    public String delivery(Model model){
        model.addAttribute("deliveries", deliveryService.fetchAllDelivery());
        return "admin/delivery";
    }

    @GetMapping("/createDelivery")
    public String createDelivery(){
        return "admin/createDelivery";
    }
    @PostMapping("/createDeliveryPost")
    public String createDeliveryPost(@ModelAttribute Delivery delivery){
        deliveryService.createDelivery(delivery);
        return "redirect:/admin/delivery";
    }

    @GetMapping("/updateDelivery/{id}")
    public String updateDelivery(@PathVariable int id, Model model){
        model.addAttribute("delivery", deliveryService.fetchOneDelivery(id));
        return "admin/updateDelivery";
    }

    @PostMapping("/updateDeliveryPost")
    public String updateDeliveryPost(@ModelAttribute Delivery delivery){
        deliveryService.updateDelivery(delivery);
        return "redirect:/admin/delivery";
    }

    @GetMapping("/deleteDelivery/{id}")
    public String deleteDelivery(@PathVariable int id){
        deliveryService.deleteDelivery(id);
        return "redirect:/admin/delivery";
    }

    //Admin Betaling
    @GetMapping("/payment")
    public String payment(Model model){
        model.addAttribute("payments", paymentService.fetchAllPayments());
        return "admin/payment";
    }

    @GetMapping("/createPayment")
    public String createPayment(){
        return "admin/createPayment";
    }
    @PostMapping("/createPaymentPost")
    public String createPaymentPost(@ModelAttribute Payment payment){
        paymentService.createPayment(payment);
        return "redirect:/admin/payment";
    }

    @GetMapping("/updatePayment/{id}")
    public String updatePayment(@PathVariable int id, Model model){
        model.addAttribute("payment", paymentService.fetchOnePayment(id));
        return "admin/updatePayment";
    }

    @PostMapping("/updatePaymentPost")
    public String updatePaymentPost(@ModelAttribute Payment payment){
        paymentService.updatePayment(payment);
        return "redirect:/admin/payment";
    }

    @GetMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable int id){
        paymentService.deletePayment(id);
        return "redirect:/admin/payment";
    }
}