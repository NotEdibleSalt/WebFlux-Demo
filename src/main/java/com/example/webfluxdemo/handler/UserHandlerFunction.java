package com.example.webfluxdemo.handler;

import com.example.webfluxdemo.entity.User;
import com.example.webfluxdemo.repo.UserRepository;
import com.example.webfluxdemo.validator.UserValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**

 * @version 1.0
 * @date 2021-8-16
 */
@Component
public class UserHandlerFunction {

    @Resource
    private UserRepository userRepository;

    public Mono<ServerResponse> createUser(ServerRequest request) {

        // 使用doOnNext方法在获取参数时进行验证
        Mono<User> userMono = request.bodyToMono(User.class).doOnNext(this::validate);
        Flux<Integer> integerFlux = userRepository.saveAll(userMono).map(User::getId);
        return ServerResponse.ok().body(integerFlux, Integer.class);
    }


    public Mono<ServerResponse> getUsers(ServerRequest request) {

        Flux<User> userFlux = userRepository.findAll();
        return ServerResponse.ok().body(userFlux, User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {

        String id = request.pathVariable("id");
        Mono<User> userMono = userRepository.findById(Integer.valueOf(id));
        return ServerResponse.ok().body(userMono, User.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {

        Mono<User> userMono = request.bodyToMono(User.class);
        userRepository.saveAll(userMono);
        return ServerResponse.ok().bodyValue("修改成功");
    }

    public Mono<ServerResponse> delUser(ServerRequest request) {

        String id = request.pathVariable("id");
        userRepository.deleteById(Integer.valueOf(id));
        return ServerResponse.ok().bodyValue("删除成功");
    }

    // 定义验证方法
    private void validate(User user) {

        // 实例化实体验证器
        Validator validator = new UserValidator();
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

}
