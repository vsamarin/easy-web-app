package ru.vsamarin.easy_web_app.rest.exception;

import ru.vsamarin.easy_web_app.bll.dto.ApiExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        switch (e.getType()) {
            case NO_FOUND:
                return Response.noContent()
                        .entity(new ApiExceptionDto(e.getMessage()))
                        .build();
            default:
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new ApiExceptionDto(e.getMessage()))
                        .build();
        }
    }
}
