package net.gdogra.easynotifications.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gdogra.easynotifications.dtos.LoginDto;
import net.gdogra.easynotifications.appuser.AppUserService;
import net.gdogra.easynotifications.dtos.RegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final AppUserService appUserService;

    @GetMapping({"/", "/register"})
    public String home(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerNewUser(@Valid @ModelAttribute("registerDto") RegisterDto registerDto,
                           BindingResult result) {
        log.info("/registerDto validation------------------------");
        log.info(String.valueOf(registerDto));
        if (result.hasErrors()) {
            log.info("errors found.......................");
            return "register";
        } else {
            log.info("Returning Success...................");
            return "success";
        }
    }

//    @PostMapping(value = "/", params = "action=login")
//    public String login(@Valid AppUserDto dto) {
//        log.info("LOGIN");
//        log.info(dto.getEmail());
//        log.info(dto.getPassword());
//        return "success";
//    }
}
