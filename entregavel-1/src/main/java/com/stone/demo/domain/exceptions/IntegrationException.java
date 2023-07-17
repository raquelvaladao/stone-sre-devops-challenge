package com.stone.demo.domain.exceptions;


import com.stone.demo.view.dto.GeneralError;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntegrationException extends RuntimeException {

    private final GeneralError error;

    public IntegrationException(String message, GeneralError error) {
        super(message);
        this.error = error;
    }

    public IntegrationException(GeneralError error) {
        this.error = error;
    }
}
