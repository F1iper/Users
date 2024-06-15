package com.protasker.users.service.impl;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.repository.AppUserRepository;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceImplTest {


    @Mock
    private AppUserMapper mapper;

    @Mock
    private AppUserRepository repository;

    @InjectMocks
    private AppUserServiceImpl service;


    @Test
    void shouldCreateCorrectUser() {
        // given
        CreateAppUserRequest request = CreateAppUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        AppUser savedUser = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        CreateAppUserResponse expectedResponse = CreateAppUserResponse.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();

        when(mapper.toEntityFromRequest(request)).thenReturn(savedUser);
        when(repository.save(savedUser)).thenReturn(savedUser);
        when(mapper.toResponseFromEntity(savedUser)).thenReturn(expectedResponse);

        // when
        service.createUser(request);

        // then
        assertAll(
                () -> assertThat(expectedResponse).isNotNull(),
                () -> assertThat(expectedResponse.getId()).isEqualTo(1L),
                () -> assertThat(expectedResponse.getFirstName()).isEqualTo("John"),
                () -> assertThat(expectedResponse.getLastName()).isEqualTo("Doe"),
                () -> assertThat(expectedResponse.getEmail()).isEqualTo("john.doe@example.com")
        );

        verify(repository, times(1)).save(savedUser);
        verify(mapper, times(1)).toResponseFromEntity(savedUser);
    }

    @Test
    public void shouldGetUserById() {
        // given
        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .password("password")
                .build();

        AppUserDto dto = AppUserDto.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .build();

        when(repository.save(user)).thenReturn(user);
        when(service.getUserById(1L)).thenReturn(dto);
        repository.save(user);

        // when
        AppUserDto userById = service.getUserById(1L);

        // then
        assertAll(
                () -> assertThat(userById).isNotNull(),
                () -> assertThat(userById.getId()).isEqualTo(1L),
                () -> assertThat(userById.getFirstName()).isEqualTo("Jane"),
                () -> assertThat(userById.getLastName()).isEqualTo("Doe"),
                () -> assertThat(userById.getEmail()).isEqualTo("jane.doe@example.com")
        );
    }
}
