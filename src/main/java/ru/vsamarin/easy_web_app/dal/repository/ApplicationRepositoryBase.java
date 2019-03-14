package ru.vsamarin.easy_web_app.dal.repository;

import ru.vsamarin.easy_web_app.dal.entity.EntityBase;
import ru.vsamarin.easy_web_app.dal.util.PersistenceUtil;

import javax.persistence.EntityManager;

public abstract class ApplicationRepositoryBase<TEntity extends EntityBase> extends RepositoryBase<TEntity> {

    @Override
    protected EntityManager em() {
        return PersistenceUtil.createEntityManager();
    }
}
