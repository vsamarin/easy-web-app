package ru.vsamarin.easy_web_app.bll.converter;

import ru.vsamarin.easy_web_app.bll.dto.UserDto;
import ru.vsamarin.easy_web_app.dal.entity.UserEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserDtoListConverter implements Converter<List<UserEntity>, List<UserDto>> {

    @Override
    public List<UserDto> convert(List<UserEntity> entityList) {
        return entityList == null ?
                Collections.emptyList() :
                entityList.stream().map(this::convert).collect(Collectors.toList());
    }

    private UserDto convert(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .title(entity.getTitle())
                .email(entity.getEmail())
                .build();
    }
}
