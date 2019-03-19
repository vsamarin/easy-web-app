package ru.vsamarin.easy_web_app.bll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiExceptionDto {
    private String message;
}
