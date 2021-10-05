package net.gdogra.easynotifications.appuser;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
public class AppUser implements UserDetails {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;


    @NotBlank(message = "email can not be left blank")
    @Size(min=2, max=60, message="First name between 2 and 60 characters")
    private String email; // email as username

    @NotBlank(message = "password can not be left blank")
    @Size(min=6, max=20, message="password between 6 and 20 characters")
    private String password;
//    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AppUser() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        return email.equals(appUser.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
