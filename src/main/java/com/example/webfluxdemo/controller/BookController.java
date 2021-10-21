package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.entity.Book;
import com.example.webfluxdemo.repo.BookRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**

 * @version 1.0
 * @date 2021-8-12
 */
@RestController
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @PostMapping("books")
    public Mono<Integer> createUser(@RequestBody @Validated Book book){

        return bookRepository.save(book).map(Book::getId);
    }

    @GetMapping("books")
    public Flux<Book> getUsers(){

        return bookRepository.findAll();
    }

    @GetMapping("books/{id}")
    public Mono<Book> getUserById(@PathVariable("id") Integer id){

        return bookRepository.findById(id);
    }

    @PutMapping("books")
    public Mono<String> updateUser(@RequestBody Book book){

        bookRepository.save(book);
        return Mono.just("修改成功");
    }

    @DeleteMapping("books/{id}")
    public Mono<String> delUser(@PathVariable("id") Integer id){

        bookRepository.deleteById(id);
        return Mono.just("删除成功");
    }

}
