package com.protasker.users.mapper;

import com.protasker.users.entity.AppUser;
import com.protasker.users.request.CreateAppUserRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateAppUserRequestMapper {

    public AppUser mapToUser(CreateAppUserRequest request) {
        return AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
