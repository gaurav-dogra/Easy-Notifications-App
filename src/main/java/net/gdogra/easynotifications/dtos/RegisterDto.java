package net.gdogra.easynotifications.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Size(min = 10)
    private String mobile;

    @NotBlank(message = "email can not be left blank")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email address")
    private String email;

    @NotBlank(message = "password can not be left blank")
    @Size(min=6, max=20, message="password must be between 6 and 20 characters")
    private String password;
}