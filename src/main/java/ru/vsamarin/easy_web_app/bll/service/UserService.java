package ru.vsamarin.easy_web_app.bll.service;

import ru.vsamarin.easy_web_app.bll.converter.Converter;
import ru.vsamarin.easy_web_app.bll.converter.UserDtoListConverter;
import ru.vsamarin.easy_web_app.bll.dto.ListDto;
import ru.vsamarin.easy_web_app.bll.dto.UserDto;
import ru.vsamarin.easy_web_app.bll.validator.IdValidator;
import ru.vsamarin.easy_web_app.bll.validator.UserDtoValidator;
import ru.vsamarin.easy_web_app.dal.entity.UserEntity;
import ru.vsamarin.easy_web_app.dal.filter.UserFilter;
import ru.vsamarin.easy_web_app.dal.repository.RepositoryBase;
import ru.vsamarin.easy_web_app.dal.repository.UserRepository;
import ru.vsamarin.easy_web_app.rest.exception.ApiException;

import java.util.Collections;
import java.util.List;

public class UserService extends ServiceBase {

    private RepositoryBase<UserEntity> repository = new UserRepository();
    private Converter<List<UserEntity>, List<UserDto>> converter = new UserDtoListConverter();

    public ListDto<UserDto> getAll() {
        Long total = repository.getCount();
        List<UserEntity> entityList = repository.getAll();
        List<UserDto> dtoList = converter.convert(entityList);
        return new ListDto<>(total, dtoList);
    }

    public ListDto<UserDto> getList(UserFilter filter) throws ApiException {
        Long total = repository.getCount(filter);
        List<UserEntity> entityList = repository.getList(filter);
        List<UserDto> dtoList = converter.convert(entityList);
        return new ListDto<>(total, dtoList);
    }

    public UserDto getById(Long id) throws ApiException {
        validate(id, new IdValidator());
        UserEntity entity = repository.getById(id);
        if (entity == null) {
            throw new ApiException(ApiException.Type.NO_FOUND, String.format("entity with id \"%s\" not found", id));
        }
        List<UserDto> dtoList = converter.convert(Collections.singletonList(entity));
        return dtoList.get(0);
    }

    public UserDto save(UserDto dto) throws ApiException {
        validate(dto, new UserDtoValidator());
        UserEntity entity;
        if (dto.getId() == null) {
            entity = new UserEntity();
        } else {
            entity = repository.getById(dto.getId());
        }
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setTitle(dto.getTitle());
        entity = repository.save(entity);
        List<UserDto> dtoList = converter.convert(Collections.singletonList(entity));
        return dtoList.get(0);
    }

    public void delete(Long id) throws ApiException {
        validate(id, new IdValidator());
        UserEntity entity = repository.getById(id);
        if (entity == null) {
            throw new ApiException(ApiException.Type.NO_FOUND, String.format("entity with id \"%s\" not found", id));
        }
        repository.delete(entity);
    }
}
