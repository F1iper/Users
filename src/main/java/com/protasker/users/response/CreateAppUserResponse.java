package com.protasker.users.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAppUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
