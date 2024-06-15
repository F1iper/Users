package com.protasker.users.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAppUserRequest {

    @NotNull(message = "Firstname cannot be null.")
    @Size(min = 2, message = "Firstname must not be less than two characters")
    private String firstName;
    @NotNull(message = "Lastname cannot be null.")
    @Size(min = 2, message = "Lastname must not be less than two characters")
    private String lastName;
    @NotNull(message = "Password cannot be null.")
    @Size(min = 8, max = 16, message = "Password must equal or greater than 8 characters and less or equal 16 characters")
    private String password;
    @NotNull(message = "Email cannot be null.")
    @Email
    private String email;

}
