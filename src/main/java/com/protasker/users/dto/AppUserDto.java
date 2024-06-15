package com.protasker.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class AppUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -953297098295050686L;
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String encryptedPassword;
    private String userId;
}
