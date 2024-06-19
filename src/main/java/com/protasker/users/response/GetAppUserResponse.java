package com.protasker.users.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAppUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
