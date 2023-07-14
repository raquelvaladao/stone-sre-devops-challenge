package com.stone.demo.domain.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.demo.domain.exceptions.IntegrationException;
import com.stone.demo.domain.mappers.UserMapper;
import com.stone.demo.domain.models.User;
import com.stone.demo.domain.repositories.UserRepository;
import com.stone.demo.domain.services.DataValidationService;
import com.stone.demo.domain.services.UserService;
import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.LogInfo;
import com.stone.demo.view.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final DataValidationService dataValidation;

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final ObjectMapper objectMapper;

    public UserServiceImpl(DataValidationService dataValidation, UserRepository userRepository, UserMapper mapper, ObjectMapper objectMapper) {
        this.dataValidation = dataValidation;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<Object> execute(UserRequest userRequest, String requestId) {
        requestId = Strings.isBlank(requestId) ? UUID.randomUUID().toString() : requestId;

            log.info("{}", LogInfo.builder()
                    .operation("[ UserServiceImpl ] - Insert user")
                    .date(LocalDate.now())
                    .requestId(requestId)
                    .entity(userRequest)
                    .build()
            );

        List<GeneralError> fieldErrors = dataValidation.validate(userRequest);
        if (!fieldErrors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(fieldErrors);
        }

        User user = mapper.requestToEntity(userRequest);

        log.info("Save attempt to database... {}", userRequest);

        userRepository.save(user);

        log.info("Saved user to database...");

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToResponse(user));
    }

    @Override
    public ResponseEntity<Object> execute(String cpf, String requestId) {
        requestId = Strings.isBlank(requestId) ? UUID.randomUUID().toString() : requestId;

        log.info("{}", LogInfo.builder()
                .operation("[ UserServiceImpl ] - Find user")
                .date(LocalDate.now())
                .requestId(requestId)
                .build()
        );

        if (dataValidation.isCPFInvalid(cpf))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new GeneralError("INVALID_CPF", "FIELD_IS_INVALID"));

        try {
            Optional<User> user = userRepository.findById(cpf);
            log.info("findById - Operation completed.");

            if (!user.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        } catch (Exception e) {
            throw new IntegrationException(new GeneralError("SYSTEM_ERROR", "System error"));
        }

    }

    @Override
    public ResponseEntity<Object> execute(String requestId) {
        requestId = Strings.isBlank(requestId) ? UUID.randomUUID().toString() : requestId;

        log.info("{}", LogInfo.builder()
                .operation("[ UserServiceImpl ] - Find all users")
                .date(LocalDate.now())
                .requestId(requestId)
                .build()
        );

        try {
           List<User> users = userRepository.findAll();
            log.info("findAll - Operation completed.");

            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            throw new IntegrationException(new GeneralError("SYSTEM_ERROR", "System error"));
        }
    }


}
