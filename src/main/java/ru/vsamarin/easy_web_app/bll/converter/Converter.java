package ru.vsamarin.easy_web_app.bll.converter;

public interface Converter<TEntity, TDto> {
    TDto convert(TEntity entity);
}
