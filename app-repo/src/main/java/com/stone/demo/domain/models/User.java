package com.stone.demo.domain.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @Column(name = "U_CPF", nullable = false)
    private String cpf;

    @Column(name = "U_FORENAME")
    private String forename;

    @Column(name = "U_SURNAME")
    private String surname;

    @Column(name = "U_EMAIL")
    private String email;

    @Column(name = "U_BIRTHDATE")
    private Date birthDate;
}
