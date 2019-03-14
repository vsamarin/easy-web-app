package ru.vsamarin.easy_web_app.dal.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import ru.vsamarin.easy_web_app.dal.entity.UserEntity;

import javax.persistence.criteria.Predicate;

@Getter
@Setter
public class UserFilter extends FilterBase<UserEntity> {
    private String login;
    private String title;
    private String email;

    @Override
    public Predicate apply(CriteriaQueryContainer<UserEntity> c) {
        Predicate p = null;

        if (StringUtils.isNotEmpty(login)) {
            p = c.getBuilder().equal(c.getBuilder().lower(c.getRoot().get("login")), login.toLowerCase());
        }

        if (StringUtils.isNotEmpty(title)) {
            Predicate newPredicate = c.getBuilder().like(
                    c.getBuilder().lower(c.getRoot().get("email")),
                    "%" + email.toLowerCase() + "%s"
            );
            p = p == null ? newPredicate : c.getBuilder().and(p, newPredicate);
        }

        if (StringUtils.isNotEmpty(email)) {
            Predicate newPredicate = c.getBuilder().equal(
                    c.getBuilder().lower(c.getRoot().get("email")),
                    email.toLowerCase()
            );
            p = p == null ? newPredicate : c.getBuilder().and(p, newPredicate);
        }

        return p;
    }
}
