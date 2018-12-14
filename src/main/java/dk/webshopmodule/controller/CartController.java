package dk.webshopmodule.controller;

import dk.webshopmodule.mail.Mail;
import dk.webshopmodule.model.*;
import dk.webshopmodule.repository.IStatusRepo;
import dk.webshopmodule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    IProductService productService;

    @Autowired
    IDeliveryService deliveryService;

    @Autowired
    IPaymentService paymentService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IStatusRepo statusRepo;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IOrderlineService orderlineService;

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

    @GetMapping("/buy")
    public String buy(HttpSession session, Model model){
        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
        double total = 0;
        if (cart != null) {
            for (OrderLine i : cart) {
                total += i.getProduct().getSalesPrice() * i.getQuantity();
            }
            model.addAttribute("total", total);
        }
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("deliveries", deliveryService.fetchAllDelivery());
        model.addAttribute("payments", paymentService.fetchAllPayments());
        return "cart/buy";
    }

    @PostMapping("/finalizeOrder")
    public String confirmOrder(HttpSession session, Model model,
                                @ModelAttribute Customer customer,
                                @ModelAttribute Payment payment,
                                @ModelAttribute Delivery delivery,
                                @RequestParam("same") boolean same){
        if (same){
            customer.setdAdress(customer.getiAdress());
            customer.setdZipcode(customer.getiZipcode());
            customer.setdTown(customer.getiTown());
            customer.setdCountry(customer.getiCountry());
        }

        model.addAttribute("total",getTotal(session)+delivery.getPrice());
        model.addAttribute("customer", customer);
        model.addAttribute("payment", payment);
        model.addAttribute("delivery", delivery);

        return "cart/confirm";
    }

    @PostMapping("/confirmed")
    public String confirmedOrder(@ModelAttribute Customer customer,
                                 @ModelAttribute Payment payment,
                                 @ModelAttribute Delivery delivery,
                                 @RequestParam("total") double total,
                                 HttpSession session,
                                 Model model){
        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
        List<Status> statuses = statusRepo.findAll();
        Order order = new Order(delivery, customer, total, Calendar.getInstance().getTime(), statuses.get(0), payment, cart);
        order.getCustomer().setId(customerService.addCustomer(customer));
        int orderNumber = orderService.addOrder(order);
        order.setId(orderNumber);

        for (OrderLine i: cart){
            i.setOrder(order);
            i.setProductPrice(i.getProduct().getSalesPrice());
        }

        orderlineService.addOrderlines(cart);

        Mail mail = new Mail();
        mail.sendNewOrderCustomer(customer.getEmail());
        mail.sendNewOrderAdmin();

        return "redirect:/cart/thanks/"+orderNumber;
    }

    @GetMapping("/thanks/{orderNumber}")
    public String thanks(@PathVariable int orderNumber, Model model){
        model.addAttribute("orderNumber", orderNumber);
        return "cart/confirmed";
    }

    private int exists(String id, List<OrderLine> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public double getTotal(HttpSession session){
        List<OrderLine> cart = (List<OrderLine>) session.getAttribute("cart");
        double total = 0;
        if (cart != null) {
            for (OrderLine i : cart) {
                total += i.getProduct().getSalesPrice() * i.getQuantity();
            }
            return total;
        }
        return 0;
    }
}