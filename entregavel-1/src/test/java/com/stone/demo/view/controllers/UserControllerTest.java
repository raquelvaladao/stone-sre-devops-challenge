package com.stone.demo.view.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.stone.demo.builders.UserBuilder;
import com.stone.demo.domain.services.UserService;
import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.UserRequest;
import com.stone.demo.view.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUserSuccessfully() throws Exception {
        UserRequest userRequest = UserBuilder.builderUserRequest().build();
        UserResponse userResponse = new UserResponse();
        userResponse.setCpf(userRequest.getCpf());

        Mockito.when(userService.execute(Mockito.any(UserRequest.class), Mockito.any()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(userResponse));

        MvcResult result = mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(userResponse), result.getResponse().getContentAsString());
    }

    @Test
    void testCreateUserValidationError() throws Exception {
        UserRequest userRequest = UserBuilder.builderUserRequest().cpf(null).build();
        List<GeneralError> errors = new ArrayList<>();
        errors.add(new GeneralError("INVALID_CPF", "Field is invalid"));

        Mockito.when(userService.execute(Mockito.any(UserRequest.class), Mockito.any()))
                .thenReturn(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors));

        MvcResult result = mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(errors),
                result.getResponse().getContentAsString());
    }
}
