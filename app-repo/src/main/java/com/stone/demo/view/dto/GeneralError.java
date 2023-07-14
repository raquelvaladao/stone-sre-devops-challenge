package com.stone.demo.view.dto;

import lombok.*;
import org.joda.time.LocalDate;


@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GeneralError {

    private LocalDate date;

    private String code;
    private String message;

    public GeneralError(String code, String message) {
        this.date = LocalDate.now();
        this.code = code;
        this.message = message;
    }
}
