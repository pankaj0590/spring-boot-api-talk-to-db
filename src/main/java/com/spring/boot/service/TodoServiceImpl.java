package com.spring.boot.service;

import com.spring.boot.beans.TodoEntity;
import com.spring.boot.dto.Result;
import com.spring.boot.dto.TodoDto;
import com.spring.boot.dto.TodoListDto;
import com.spring.boot.dto.TodoResponse;
import com.spring.boot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl  implements TodoService{

    @Autowired
    TodoRepository todoRepository;
    HttpStatus status= HttpStatus.BAD_REQUEST;
    @Override
    public ResponseEntity<TodoResponse> createTodo(TodoDto todoDto) {
        TodoResponse todoResponse = new TodoResponse();

        TodoEntity todoEntity =new TodoEntity();
        todoEntity.setDescription(todoDto.getDescription());
        todoEntity.setCreatedDate(new Date());
        if(todoRepository.save(todoEntity).getId()!=null){
            todoResponse.setResult(new Result("Todo Created Successfully"));
            status=HttpStatus.CREATED;
        }
        return new ResponseEntity<>(todoResponse,status);
    }

    @Override
    public ResponseEntity<TodoResponse> findAllTodo() {

        List<TodoEntity> entities = todoRepository.findAll();
        List<TodoListDto> listObj = new ArrayList<>();
        TodoResponse finalResponse = new TodoResponse();
        if(!CollectionUtils.isEmpty(entities)){

            entities.forEach(data->{
                TodoListDto todoListDto= new TodoListDto();
                todoListDto.setId(data.getId());
                todoListDto.setDescription(data.getDescription());
                todoListDto.setCreatedDate(data.getCreatedDate());
                todoListDto.setUpdatedDate(data.getUpdatedDate());
                listObj.add(todoListDto);
            });
            finalResponse.setData(listObj);
            finalResponse.setResult(new Result("Todo data returned successfully"));
            status=HttpStatus.OK;
            }

        return new ResponseEntity<>(finalResponse,status);
    }
}
