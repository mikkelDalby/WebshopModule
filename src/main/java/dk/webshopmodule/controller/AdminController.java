package dk.webshopmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @GetMapping("")
    public String admin(HttpServletRequest request){
        if (request.isUserInRole("ADMIN")){
            return "admin/index";
        }
        return "redirect:/redirect";
    }
}
