package ru.vsamarin.easy_web_app.dal.filter;

import lombok.Getter;
import lombok.Setter;
import ru.vsamarin.easy_web_app.dal.entity.EntityBase;

import javax.persistence.criteria.Predicate;

@Getter
@Setter
public abstract class FilterBase<TEntity extends EntityBase> {

    private Integer page = 1;
    private Integer recordsOnPage = 5;
    private String orderBy;
    private Boolean ascending = true;

    public abstract Predicate apply(CriteriaQueryContainer<TEntity> container);

    public Integer getOffset() {
        return (recordsOnPage != null && page != null)
                ? ((page < 1 ? 1 : page) - 1) * recordsOnPage
                : null;
    }
}