package ru.vsamarin.easy_web_app.bll.service;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import ru.vsamarin.easy_web_app.bll.validator.ValidatorBase;
import ru.vsamarin.easy_web_app.rest.exception.ApiException;

public abstract class ServiceBase {

    protected <T> void validate(T dto, ValidatorBase<T> validator) throws ApiException {
        Result result = FluentValidator.checkAll().failFast()
                .on(dto, validator)
                .doValidate()
                .result(ResultCollectors.toSimple());
        if (!result.isSuccess()) {
            throw new ApiException(result.getErrors().get(0));
        }
    }
}
