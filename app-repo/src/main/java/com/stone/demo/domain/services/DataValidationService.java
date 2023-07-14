package com.stone.demo.domain.services;

import com.stone.demo.view.dto.GeneralError;
import com.stone.demo.view.dto.UserRequest;

import java.util.List;

public interface DataValidationService {

    List<GeneralError> validate(UserRequest userRequest);

    boolean isCPFInvalid(String cpf);
}
