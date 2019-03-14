package ru.vsamarin.easy_web_app.bll.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListDto<TDto extends DtoBase> {
    private Long total;
    private List<TDto> data;
}
