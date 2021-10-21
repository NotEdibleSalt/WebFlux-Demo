package com.example.webfluxdemo.route;

import com.example.webfluxdemo.handler.UserHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**

 * @version 1.0
 * @date 2021-8-16
 */
@Configuration
@EnableWebFlux
public class WebFLuxRoute implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> userRouterFunction(UserHandlerFunction userHandlerFunction) {

        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route()
                .GET("/users/{id}", userHandlerFunction::getUserById)
                .GET("/users", userHandlerFunction::getUsers)
                .POST("/users", userHandlerFunction::createUser)
                .PUT("/users", userHandlerFunction::updateUser)
                .DELETE("/users/{id}", userHandlerFunction::delUser)
                .build();

        return routerFunction;
    }

}
