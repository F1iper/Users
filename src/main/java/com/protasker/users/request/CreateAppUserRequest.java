package com.protasker.users.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppUserRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
