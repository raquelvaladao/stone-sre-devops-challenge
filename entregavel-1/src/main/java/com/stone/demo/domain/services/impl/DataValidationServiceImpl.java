package com.stone.demo.domain.services.impl;

import com.stone.demo.domain.services.DataValidationService;
import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class DataValidationServiceImpl implements DataValidationService {

    public static final String OBJECT_IS_INVALID = "Object is invalid";
    private static final String EMAIL_REGEX = "[\\w-\\.]+@([\\w-]+\\.){2,4}[\\w-]{2,4}$";
    private static final String EMAIL_REGEX_FINAL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String FIELD_IS_INVALID = "Field is invalid";

    @Override
    public List<GeneralError> validate(UserRequest userRequest) {
        List<GeneralError> errors = new ArrayList<>();

        log.info("[ DataValidationServiceImpl ] - Validating fields.");

        if (Objects.isNull(userRequest)) {
            errors.add(new GeneralError("INVALID_PAYLOAD", OBJECT_IS_INVALID));
            return errors;
        }

        if (Strings.isBlank(userRequest.getSurname()))
            errors.add(new GeneralError("INVALID_SURNAME", FIELD_IS_INVALID));

        if (Strings.isBlank(userRequest.getForename()))
            errors.add(new GeneralError("INVALID_FORENAME", FIELD_IS_INVALID));

        if (Objects.isNull(userRequest.getBirthDate()))
            errors.add(new GeneralError("INVALID_BIRTHDATE", FIELD_IS_INVALID));

        if (isCPFInvalid(userRequest.getCpf()))
            errors.add(new GeneralError("INVALID_CPF", FIELD_IS_INVALID));

        if (isEmailInvalid(userRequest.getEmail()))
            errors.add(new GeneralError("INVALID_EMAIL", FIELD_IS_INVALID));

        return errors;
    }

    @Override
    public boolean isCPFInvalid(String cpf) {
        if (Strings.isBlank(cpf))
            return true;

        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return true;

        return isCPFWeightInvalid(cpf);
    }

    private boolean isEmailInvalid(String email) {
        return Strings.isBlank(email) || !email.matches(EMAIL_REGEX);
    }

    private static boolean isCPFWeightInvalid(String cpf) {
        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            return (dig10 != cpf.charAt(9)) || (dig11 != cpf.charAt(10));
        } catch (InputMismatchException ex) {
            return true;
        }
    }
}
