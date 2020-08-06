package com.spring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TodoListDto {
    private Integer id;
    private String description;
    private Date createdDate;
    private Date updatedDate;
}
