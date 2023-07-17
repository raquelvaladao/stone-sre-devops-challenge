package com.stone.demo.domain.exceptions;


import com.stone.demo.view.dto.GeneralError;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnprocessableEntityException extends RuntimeException {

    private final GeneralError error;

    public UnprocessableEntityException(String message, GeneralError error) {
        super(message);
        this.error = error;
    }

    public UnprocessableEntityException(GeneralError error) {
        this.error = error;
    }
}
