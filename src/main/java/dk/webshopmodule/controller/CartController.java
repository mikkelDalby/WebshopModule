package dk.webshopmodule.controller;

import dk.webshopmodule.model.OrderLine;
import dk.webshopmodule.model.Product;
import dk.webshopmodule.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    IProductService productService;

    private List<Product> products;

    public Product findProduct(String id){
        for (Product p: this.products){
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
        double total = 0;
        if (cart != null) {
            for (OrderLine i : cart) {
                total += i.getProduct().getSalesPrice() * i.getQuantity();
            }
            model.addAttribute("total", total);
        }
        model.addAttribute("products", productService.getAllProducts());
        return "cart/index";
    }

    @GetMapping("/sub/{id}")
    public String buy(@PathVariable("id") String id, HttpSession session) {
        products = productService.getAllProducts();
        Product product = findProduct(id);
        if (product != null) {
            if (session.getAttribute("cart") == null) {
                List<OrderLine> cart = new ArrayList<>();
                cart.add(new OrderLine(product, 1));
                session.setAttribute("cart", cart);
            } else {
                List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
                int index = -1;
                for (int i = 0; i < cart.size(); i++){
                    if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)){
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    cart.add(new OrderLine(product, 1));
                } else {
                    int q = cart.get(index).getQuantity() - 1;
                    cart.get(index).setQuantity(q);
                }
                session.setAttribute("cart", cart);
            }
        }
        return "redirect:/cart/";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") String id, HttpSession session){
        products = productService.getAllProducts();
        Product product = findProduct(id);
        if (product != null) {
            if (session.getAttribute("cart") == null) {
                List<OrderLine> cart = new ArrayList<>();
                cart.add(new OrderLine(product, 1));
                session.setAttribute("cart", cart);
            } else {
                List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
                int index = -1;
                for (int i = 0; i < cart.size(); i++){
                    if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)){
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    cart.add(new OrderLine(product, 1));
                } else {
                    int q = cart.get(index).getQuantity() + 1;
                    cart.get(index).setQuantity(q);
                }
                session.setAttribute("cart", cart);
            }
        }
        return "redirect:/cart/";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id, HttpSession session) {
        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart/";
    }

    private int exists(String id, List<OrderLine> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}