package com.protasker.users.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAppUserRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
