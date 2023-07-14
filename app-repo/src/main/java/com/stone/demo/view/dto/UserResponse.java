package com.stone.demo.view.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class UserResponse {
    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("forename")
    private String forename;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("birthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthDate;

    public UserResponse() {
    }

    public UserResponse(String cpf, String forename, String surname, String email, Date birthDate) {
        this.cpf = cpf;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }
}
