package com.protasker.users.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
