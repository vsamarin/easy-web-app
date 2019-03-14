package ru.vsamarin.easy_web_app;

import io.swagger.jaxrs.config.BeanConfig;
import ru.vsamarin.easy_web_app.rest.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class ApplicationConfiguration extends Application {

    public ApplicationConfiguration() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/rest");
        beanConfig.setTitle("Easy REST Application");
        beanConfig.setDescription("Swagger-UI compatible");
        beanConfig.setResourcePackage("ru.vsamarin.easy_web_app.rest");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>(super.getClasses());
        registerClasses(classes);
        return classes;
    }

    private static void registerClasses(Set<Class<?>> classes) {
        /* swagger */
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        /* services */
        classes.add(UserController.class);
    }

}
