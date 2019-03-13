package ru.vsamarin.easy_web_app.controller;

import io.swagger.annotations.Api;
import ru.vsamarin.easy_web_app.dto.UserDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/user")
@Api(value = "UserController")
public class UserController {

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> list() {
        return Arrays.asList(
                new UserDto("vsamarin", "Самарин Владислав Сергеевич", "vsamarin@yandex.ru"),
                new UserDto("ssamarin", "Самарин Сергей Валентинович", "ssamarin@yandex.ru"),
                new UserDto("msamarin", "Самарина Маргарита Геннадьевна", "msamarin@yandex.ru")
        );

    }

}
