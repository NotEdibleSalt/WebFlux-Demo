package com.example.webfluxdemo.repo;

import com.example.webfluxdemo.entity.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**

 * @version 1.0
 * @date 2021-8-12
 */
public interface BookRepository extends R2dbcRepository<Book, Integer> {
}
