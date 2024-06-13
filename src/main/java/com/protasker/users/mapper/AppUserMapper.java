package com.protasker.users.mapper;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;

public interface AppUserMapper {

    AppUser toEntityFromRequest (CreateAppUserRequest request);
    AppUserDto toDto(AppUser appUser);
    AppUser toEntity(AppUserDto dto);
    CreateAppUserResponse toResponseFromEntity(AppUser savedAppUser);
}
