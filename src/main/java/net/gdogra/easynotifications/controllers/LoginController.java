package net.gdogra.easynotifications.controllers;

import net.gdogra.easynotifications.appuser.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupNewUser(Model model) {
        model.addAttribute("user", new AppUser());
        return "signup_form";
    }
}
