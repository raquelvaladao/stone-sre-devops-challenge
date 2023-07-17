package com.stone.demo.view;


import com.stone.demo.domain.exceptions.UnprocessableEntityException;
import com.stone.demo.view.dto.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = { UnprocessableEntityException.class })
    protected ResponseEntity<Object> handleUnprocessableEntityException(UnprocessableEntityException ex, WebRequest request) {
        GeneralError error = new GeneralError("INVALID_PAYLOAD", "Invalid payload");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
