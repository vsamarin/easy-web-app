package ru.vsamarin.easy_web_app.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity extends EntityBase {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email", nullable = false)
    private String email;
}
