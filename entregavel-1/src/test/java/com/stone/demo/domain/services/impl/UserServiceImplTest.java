package com.stone.demo.domain.services.impl;

import com.stone.demo.builders.UserBuilder;
import com.stone.demo.domain.mappers.UserMapper;
import com.stone.demo.domain.models.User;
import com.stone.demo.domain.repositories.UserRepository;
import com.stone.demo.domain.services.DataValidationService;
import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.UserRequest;
import com.stone.demo.view.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DataValidationService dataValidationService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateUserSuccessfully() {
        UserRequest userRequest = UserBuilder.builderUserRequest().build();
        User entity = new User();
        entity.setCpf(userRequest.getCpf());
        UserResponse userResponse = new UserResponse();
        userResponse.setCpf(userRequest.getCpf());

        when(dataValidationService.validate(Mockito.any())).thenReturn(Collections.emptyList());
        when(mapper.requestToEntity(Mockito.any())).thenReturn(entity);
        when(userRepository.save(Mockito.any())).thenReturn(entity);
        when(mapper.entityToResponse(Mockito.any())).thenReturn(userResponse);

        ResponseEntity<Object> response = userService.execute(userRequest, "123");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userRequest.getCpf(), ((UserResponse)response.getBody()).getCpf());
    }

    @Test
    void testCreateUserFails() {
        UserRequest userRequest = UserBuilder.builderUserRequest().cpf(null).build();

        List<GeneralError> errors = new ArrayList<>();
        errors.add(new GeneralError("INVALID_CPF", "Field is invalid"));

        when(dataValidationService.validate(userRequest)).thenReturn(errors);

        ResponseEntity<Object> response = userService.execute(userRequest, null);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errors, response.getBody());
    }
}