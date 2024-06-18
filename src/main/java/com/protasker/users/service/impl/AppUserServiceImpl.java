package com.protasker.users.service.impl;

import com.protasker.users.entity.AppUser;
import com.protasker.users.mapper.AppUserMapper;
import com.protasker.users.repository.AppUserRepository;
import com.protasker.users.request.CreateAppUserRequest;
import com.protasker.users.response.CreateAppUserResponse;
import com.protasker.users.response.GetAppUserResponse;
import com.protasker.users.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserMapper mapper;
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CreateAppUserResponse createUser(CreateAppUserRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        AppUser appUserToSave = mapper.toEntityFromRequest(request);
        appUserToSave.setEncryptedPassword(encryptedPassword);

        AppUser save = repository.save(appUserToSave);
        return mapper.toCreateResponseFromEntity(save);
    }

    @Override()
    public GetAppUserResponse getUserById(Long id) {
        return mapper.toGetResponseFromEntity(repository.getReferenceById(id));
    }

    @Override
    public List<GetAppUserResponse> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toGetResponseFromEntity)
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
