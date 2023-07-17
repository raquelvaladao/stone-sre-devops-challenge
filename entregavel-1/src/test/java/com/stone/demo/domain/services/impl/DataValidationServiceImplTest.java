package com.stone.demo.domain.services.impl;

import com.stone.demo.builders.UserBuilder;
import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DataValidationServiceImplTest {

    @InjectMocks
    DataValidationServiceImpl dataValidationService;

    private static Stream<Arguments> provideUser() {
        return Stream.of(
                Arguments.of(null, "user"),
                Arguments.of(UserBuilder.builderUserRequest().email("invalid.email").build(), "email (malformated)"),
                Arguments.of(UserBuilder.builderUserRequest().email(null).build(), "email (null)"),
                Arguments.of(UserBuilder.builderUserRequest().surname(null).build(), "surname"),
                Arguments.of(UserBuilder.builderUserRequest().forename(null).build(), "forename"),
                Arguments.of(UserBuilder.builderUserRequest().birthDate(null).build(), "birthDate"),
                Arguments.of(UserBuilder.builderUserRequest().cpf(null).build(), "cpf")
        );
    }

    @Test
    void validateSuccessfullyWithoutErrors() {
        UserRequest request = UserBuilder.builderUserRequest().cpf("52998224725").build();

        List<GeneralError> errors = dataValidationService.validate(request);

        assertTrue(errors.isEmpty());
    }

    @ParameterizedTest(name = "CPF {0} is invalid")
    @ValueSource(strings = {"1", "00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999", "08550767019"})
    void validateConditionalInvalidCPFs(String cpf) {
        UserRequest request = UserBuilder.builderUserRequest().cpf(cpf).build();

        List<GeneralError> errors = dataValidationService.validate(request);

        assertFalse(errors.isEmpty());
        assertTrue(errors.get(0).getCode().contains("INVALID_CPF"));
    }

    @ParameterizedTest(name = "Field {1} is null or invalid")
    @MethodSource("provideUser")
    void validateNullFields(UserRequest user, String fieldName) {
        List<GeneralError> errors = dataValidationService.validate(user);

        assertFalse(errors.isEmpty());
        assertTrue(errors.get(0).getCode().contains("INVALID"));
    }
}