package com.spring.boot.controller;

import com.spring.boot.dto.TodoDto;
import com.spring.boot.dto.TodoResponse;
import com.spring.boot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@Controller
@RequestMapping("/home")
public class TodoController {

        @Autowired
        TodoService todoService;

        @GetMapping(value = "/ping")
        public ResponseEntity getMessage(){

                return new ResponseEntity("Pinging.. service", HttpStatus.OK);

        }

        @GetMapping(value = "/todo")
        public ResponseEntity<TodoResponse> getTodos(){

            return todoService.findAllTodo();
        }

        @PostMapping(value = "/todo")
        public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoDto todoDto){
               return todoService.createTodo(todoDto);
        }

        @GetMapping(value = "/todo-byId/{id}")
        public ResponseEntity<TodoResponse> getTodoById(@PathVariable("id") Integer id){

                return todoService.findAllTodo();
        }
}
