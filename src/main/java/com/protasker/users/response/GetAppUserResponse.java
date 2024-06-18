package com.protasker.users.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GetAppUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
