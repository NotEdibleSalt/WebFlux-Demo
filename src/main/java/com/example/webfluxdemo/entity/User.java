package com.example.webfluxdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**

 * @version 1.0
 * @date 2021-8-12
 */
@Data
@Table(value = "user")
public class User {

    @Id
    private Integer id;

    @NotBlank(message = "name为空")
    private String name;

    private LocalDate birthday;

    private String sex;

}
