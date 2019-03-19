package ru.vsamarin.easy_web_app.bll.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;

public class IdValidator extends ValidatorBase<Long> {

    @Override
    public boolean validate(ValidatorContext context, Long id) {
        if (id == null || id == 0) {
            context.addError(ValidationError.create("id is required"));
        }
        return super.validate(context, id);
    }
}
