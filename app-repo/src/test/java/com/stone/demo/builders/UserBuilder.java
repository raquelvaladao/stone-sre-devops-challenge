package com.stone.demo.builders;

import com.stone.demo.shared.utils.FormatUtils;
import com.stone.demo.view.dto.UserRequest;

public class UserBuilder {

    public static UserRequest.UserRequestBuilder builderUserRequest() {
        return UserRequest.builder()
                .cpf("87454099009")
                .birthDate(FormatUtils.strToDate("12/03/1991"))
                .email("nathan_bryan_figueiredo@consultoriosjc.com.br")
                .forename("Nathan")
                .surname("Bryan Figueiredo");
    }
}
