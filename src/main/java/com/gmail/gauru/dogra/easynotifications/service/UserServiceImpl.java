package com.gmail.gauru.dogra.easynotifications.service;

import com.gmail.gauru.dogra.easynotifications.model.Role;
import com.gmail.gauru.dogra.easynotifications.model.User;
import com.gmail.gauru.dogra.easynotifications.repo.RoleRepo;
import com.gmail.gauru.dogra.easynotifications.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the db", user.getUserName());
        return userRepo.save(user); // validation pending
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the db", role.getRoleName());
        return roleRepo.save(role); // validation pending
    }

    @Override
    public void saveRoleToUser(String userName, String roleName) {
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
}
