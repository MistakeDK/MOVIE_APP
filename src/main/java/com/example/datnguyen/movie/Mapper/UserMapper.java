package com.example.datnguyen.movie.Mapper;

import com.example.datnguyen.movie.DTO.Request.UserCreationRequest;
import com.example.datnguyen.movie.Entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper()
public interface UserMapper {
    User toUser(UserCreationRequest request);
}
