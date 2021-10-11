package net.gdogra.easynotifications.appuser;

import lombok.AllArgsConstructor;
import net.gdogra.easynotifications.dtos.LoginDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG =
            "User with email %s not found";

    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpUser(LoginDto dto) {
        boolean userExists = appUserRepo
                .findByEmail(dto.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        appUserRepo.save(new AppUser(dto.getEmail(), encodedPassword));
    }
}
