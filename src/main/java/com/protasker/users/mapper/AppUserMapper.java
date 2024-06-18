package com.protasker.users.mapper;

import com.protasker.users.entity.AppUser;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;

public interface AppUserMapper {

    AppUser toEntityFromRequest(CreateAppUserRequest request);

    CreateAppUserResponse toCreateResponseFromEntity(AppUser savedAppUser);

    GetAppUserResponse toGetResponseFromEntity(AppUser appUser);
}
