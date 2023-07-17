package com.stone.demo.view.dto;


import lombok.*;
import org.joda.time.LocalDate;



@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogInfo {

    private String requestId;

    private String operation;

    private String method;

    private Object entity;

    private LocalDate date;
}
