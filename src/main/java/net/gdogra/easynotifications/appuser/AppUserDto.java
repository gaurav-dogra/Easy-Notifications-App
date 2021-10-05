package net.gdogra.easynotifications.appuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    
    @NotBlank(message = "email can not be left blank")
    @Size(min=2, max=60, message="First name between 2 and 60 characters")
    private String email; // email as username

    @NotBlank(message = "password can not be left blank")
    @Size(min=6, max=20, message="password between 6 and 20 characters")
    private String password;
}
