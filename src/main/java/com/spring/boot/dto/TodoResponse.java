package com.spring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoResponse {
    List<TodoListDto> data;
    Result result;
}
