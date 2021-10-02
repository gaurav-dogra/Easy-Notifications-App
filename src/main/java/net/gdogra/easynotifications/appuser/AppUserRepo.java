package net.gdogra.easynotifications.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
//    Optional<AppUser> findByMobileNumber(String mobileNumber);
}
