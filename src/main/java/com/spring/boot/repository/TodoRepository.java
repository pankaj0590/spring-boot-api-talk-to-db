package com.spring.boot.repository;

import com.spring.boot.beans.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<TodoEntity ,Long> {
}
