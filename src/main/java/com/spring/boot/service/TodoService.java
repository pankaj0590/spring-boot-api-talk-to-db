package com.spring.boot.service;

import com.spring.boot.dto.TodoDto;
import com.spring.boot.dto.TodoResponse;
import org.springframework.http.ResponseEntity;


public interface TodoService {
    ResponseEntity<TodoResponse> createTodo(TodoDto todoDto);
    ResponseEntity<TodoResponse> findAllTodo();
}
