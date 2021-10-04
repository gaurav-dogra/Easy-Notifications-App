package net.gdogra.easynotifications.controllers;

import net.gdogra.easynotifications.appuser.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("")
    public String login() {
        return "signup_login";
    }

    @PostMapping("/signup")
    public String signupNewUser(Model model) {
        model.addAttribute("user", new AppUser());
        return "signup_successful";
    }

    @PostMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new AppUser());
        return "login_successful";
    }
}
