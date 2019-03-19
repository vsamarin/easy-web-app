package ru.vsamarin.easy_web_app.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        switch (e.getType()) {
            case NO_FOUND:
                return Response.noContent().entity(e).build();
            default:
                return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
}
