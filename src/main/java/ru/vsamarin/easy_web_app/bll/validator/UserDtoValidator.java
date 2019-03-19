package ru.vsamarin.easy_web_app.bll.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import org.apache.commons.lang3.StringUtils;
import ru.vsamarin.easy_web_app.bll.dto.UserDto;

public class UserDtoValidator extends ValidatorBase<UserDto> {

    @Override
    public boolean validate(ValidatorContext context, UserDto dto) {
        if (StringUtils.isEmpty(dto.getLogin())) {
            context.addError(ValidationError.create("login is required"));
        }
        if (StringUtils.isEmpty(dto.getTitle())) {
            context.addError(ValidationError.create("title is required"));
        }
        if (StringUtils.isEmpty(dto.getEmail())) {
            context.addError(ValidationError.create("email is required"));
        }
        return super.validate(context, dto);
    }

}
