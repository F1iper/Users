package com.protasker.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
