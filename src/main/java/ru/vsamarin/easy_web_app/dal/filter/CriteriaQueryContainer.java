package ru.vsamarin.easy_web_app.dal.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.vsamarin.easy_web_app.dal.entity.EntityBase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Getter
@AllArgsConstructor
public final class CriteriaQueryContainer<TEntity extends EntityBase> {
    private CriteriaBuilder builder;
    private CriteriaQuery<TEntity> query;
    private Root<TEntity> root;
}
