package com.protasker.users.service.impl;

import com.protasker.users.dto.AppUserDto;
import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.repository.AppUserRepository;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserMapper mapper;
    private final AppUserRepository repository;


    @Override
    public CreateAppUserResponse createUser(CreateAppUserRequest request) {
        AppUserDto dtoFromRequest = mapper.toDtoFromRequest(request);

        AppUser save = repository.save(mapper.toEntityFromDto(dtoFromRequest));
        AppUserDto savedDto = mapper.toDto(save);
        return mapper.toResponseFromDto(savedDto);
    }

    @Override
    public AppUserDto getUserById(Long id) {
        return mapper.toDto(repository.getReferenceById(id));
    }

    @Override
    public List<CreateAppUserResponse> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .map(mapper::toResponseFromDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppUserDto updateUser(AppUser appUser, Long id) {
        // todo: Implement patch
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
