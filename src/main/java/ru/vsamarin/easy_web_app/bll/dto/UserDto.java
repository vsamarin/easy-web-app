package ru.vsamarin.easy_web_app.bll.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends DtoBase {
    private String login;
    private String title;
    private String email;

    @Builder
    @SuppressWarnings("unused")
    public UserDto(Long id, String login, String title, String email) {
        this.id = id;
        this.login = login;
        this.title = title;
        this.email = email;
    }
}
