package com.gmail.gauru.dogra.easynotifications;

import com.gmail.gauru.dogra.easynotifications.model.Role;
import com.gmail.gauru.dogra.easynotifications.model.User;
import com.gmail.gauru.dogra.easynotifications.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class EasyNotificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyNotificationsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "myemail1@domain.com", "password1",
                    "Gaurav", "Dogra", new ArrayList<>()));
            userService.saveUser(new User(null, "myemail2@domain.com", "password2",
                    "Ayaansh", "Dogra", new ArrayList<>()));

            userService.addRoleToUser("myemail1@domain.com", "ROLE_USER");
            userService.addRoleToUser("myemail2@domain.com", "ROLE_SUPER_ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
