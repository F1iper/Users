package com.protasker.users.service.impl;

import com.protasker.users.entity.AppUser;
import com.protasker.users.repository.AppUserRepository;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {


    private final AppUserRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CreateAppUserResponse createUser(CreateAppUserRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AppUser entity = mapper.map(request, AppUser.class);
        entity.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
        entity.setUserId(UUID.randomUUID().toString());

        repository.save(entity);
        return mapper.map(entity, CreateAppUserResponse.class);
    }

    @Override()
    public GetAppUserResponse getUserById(Long id) {
        AppUser savedAppUser = repository.getReferenceById(id);

        return mapper.map(savedAppUser, GetAppUserResponse.class);
    }

    @Override
    public List<GetAppUserResponse> getAllUsers() {
        return repository.findAll().stream()
                .map(appUser -> mapper.map(appUser, GetAppUserResponse.class))
                .collect(Collectors.toList());
    }

    public GetAppUserResponse updateUser(AppUser appUser, Long id) {
        // todo: Implement patch
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
