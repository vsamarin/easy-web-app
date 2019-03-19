package ru.vsamarin.easy_web_app.bll.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

public abstract class ValidatorBase<T> extends ValidatorHandler<T> {

    @Override
    public boolean validate(ValidatorContext context, T t) {
        return context.result.getErrors() == null || context.result.getErrors().isEmpty();
    }
}
