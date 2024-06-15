package com.protasker.users.mapper.impl;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public AppUser toEntityFromRequest(CreateAppUserRequest request) {
        // todo: to be removed
        return AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    @Override
    public AppUserDto toDto(AppUser appUser) {
        return AppUserDto.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .password(appUser.getPassword())
                .encryptedPassword(appUser.getEncryptedPassword())
                .email(appUser.getEmail())
                .userId(appUser.getUserId())
                .build();
    }

    @Override
    public AppUser toEntity(AppUserDto dto) {
        return AppUser.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .userId(dto.getUserId())
                .build();
    }

    @Override
    public CreateAppUserResponse toResponseFromEntity(AppUser savedAppUser) {
        return CreateAppUserResponse.builder()
                .firstName(savedAppUser.getFirstName())
                .lastName(savedAppUser.getLastName())
                .email(savedAppUser.getEmail())
                .userId(savedAppUser.getUserId())
                .build();
    }

    @Override
    public AppUserDto toDtoFromRequest(CreateAppUserRequest request) {
        return AppUserDto.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    @Override
    public AppUser toEntityFromDto(AppUserDto dto) {
        return AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public CreateAppUserResponse toResponseFromDto(AppUserDto savedDto) {
        return CreateAppUserResponse.builder()
                .userId(savedDto.getUserId())
                .firstName(savedDto.getFirstName())
                .lastName(savedDto.getLastName())
                .email(savedDto.getEmail())
                .build();
    }
}
