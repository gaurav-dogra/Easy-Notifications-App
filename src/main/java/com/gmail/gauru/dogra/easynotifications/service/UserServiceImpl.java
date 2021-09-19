package com.gmail.gauru.dogra.easynotifications.service;

import com.gmail.gauru.dogra.easynotifications.model.Role;
import com.gmail.gauru.dogra.easynotifications.model.User;
import com.gmail.gauru.dogra.easynotifications.repo.RoleRepo;
import com.gmail.gauru.dogra.easynotifications.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the db", user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user); // validation pending
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the db", role.getRoleName());
        return roleRepo.save(role); // validation pending
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        // validations pending
        log.info("saving role {} to user {}", roleName, userName);
        User user = userRepo.findByUserName(userName);
        Role role = roleRepo.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("fetching user {}", userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            log.error("User {} not found in the db", userName);
            throw new UsernameNotFoundException("User not found in the db");
        } else {
            log.info("user {} found in the db", userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
