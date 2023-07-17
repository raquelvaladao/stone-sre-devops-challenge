package com.stone.demo.domain.services;

import com.stone.demo.view.dto.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> execute(UserRequest request, String requestId);

    ResponseEntity<Object> execute(String cpf, String requestId);

    ResponseEntity<Object> execute(String requestId);
}
