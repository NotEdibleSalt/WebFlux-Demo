package com.example.webfluxdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0
 */
@Data
@Table(value = "book")
public class Book {

    @Id
    private Integer id;

    @NotBlank(message = "name is empty")
    private String bookName;

}
