package ru.vsamarin.easy_web_app.rest;

import io.swagger.annotations.Api;
import ru.vsamarin.easy_web_app.bll.dto.ListDto;
import ru.vsamarin.easy_web_app.bll.service.UserService;
import ru.vsamarin.easy_web_app.bll.dto.UserDto;
import ru.vsamarin.easy_web_app.dal.filter.UserFilter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getById(@PathParam("id") Long id) {
        return service.getById(id);
    }
}
