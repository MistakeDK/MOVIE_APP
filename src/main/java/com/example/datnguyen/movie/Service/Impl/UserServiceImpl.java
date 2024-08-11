package com.example.datnguyen.movie.Service.Impl;

import com.example.datnguyen.movie.DTO.Request.UserCreationRequest;
import com.example.datnguyen.movie.Entity.User;
import com.example.datnguyen.movie.Enum.Role;
import com.example.datnguyen.movie.Exception.AppException;
import com.example.datnguyen.movie.Exception.ErrorCode;
import com.example.datnguyen.movie.Mapper.UserMapper;
import com.example.datnguyen.movie.Repository.UserRepository;
import com.example.datnguyen.movie.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserMapper mapper;
    UserRepository repository;
    PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void createUser(UserCreationRequest request) {
        if(repository.existsByGmail(request.getGmail())){
            throw new AppException(ErrorCode.USER_EXIST);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user= mapper.toUser(request);
        user.setRole(Role.USER);
        repository.save(user);
    }
}
