package ru.vsamarin.easy_web_app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ru.vsamarin.easy_web_app.bll.dto.ListDto;
import ru.vsamarin.easy_web_app.bll.service.UserService;
import ru.vsamarin.easy_web_app.bll.dto.UserDto;
import ru.vsamarin.easy_web_app.dal.filter.UserFilter;
import ru.vsamarin.easy_web_app.rest.exception.ApiException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Api(value = "UserController")
public class UserController {

    private UserService service = new UserService();

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ListDto<UserDto> all() {
        return service.getAll();
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ListDto<UserDto> list(UserFilter filter) {
        return service.getList(filter);
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "Not found")
    })
    public UserDto getById(@PathParam("id") Long id) throws ApiException {
        return service.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto save(UserDto dto) {
        return service.save(dto);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "Not found")
    })
    public Response delete(@PathParam("id") Long id) throws ApiException {
        service.delete(id);
        return Response.ok().build();
    }
}
