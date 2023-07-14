package com.stone.demo.domain.mappers;

import com.stone.demo.domain.models.User;
import com.stone.demo.view.dto.UserRequest;
import com.stone.demo.view.dto.UserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    User requestToEntity(UserRequest source);

    UserResponse entityToResponse(User source);
}
