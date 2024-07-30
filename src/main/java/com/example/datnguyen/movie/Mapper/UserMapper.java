package com.example.datnguyen.movie.Mapper;

import com.example.datnguyen.movie.DTO.Request.UserCreationRequest;
import com.example.datnguyen.movie.Entity.User;
import org.mapstruct.Mapper;

@Mapper()
public interface UserMapper {
    User toUser(UserCreationRequest request);
}
