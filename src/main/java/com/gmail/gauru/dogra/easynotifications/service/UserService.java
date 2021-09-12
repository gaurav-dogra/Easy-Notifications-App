package com.gmail.gauru.dogra.easynotifications.service;

import com.gmail.gauru.dogra.easynotifications.model.Role;
import com.gmail.gauru.dogra.easynotifications.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void saveRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers(); // how to provide all users with pagination
}
