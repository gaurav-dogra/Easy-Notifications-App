package com.gmail.gauru.dogra.easynotifications.repo;

import com.gmail.gauru.dogra.easynotifications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
