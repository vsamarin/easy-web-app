package ru.vsamarin.easy_web_app.rest.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends Exception {

    private Type type;

    public ApiException(String message) {
        super(message);
        this.type = Type.BAD_REQUEST;
    }

    public ApiException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public enum Type {
        NO_FOUND,
        BAD_REQUEST
    }
}
