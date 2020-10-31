package com.example.todoilst.dao;

import com.example.todoilst.model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoDao extends JpaRepository<ToDoModel, Long> {
}
