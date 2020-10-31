package com.example.todoilst.controller;


import com.example.todoilst.dao.ToDoDao;
import com.example.todoilst.model.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDoList")
public class ToDoController {

    private final ToDoDao toDoDao;

    @Autowired
    public ToDoController(ToDoDao toDoDao) {
        this.toDoDao = toDoDao;
    }

    @GetMapping("/get/all")
    public List<ToDoModel> getAll() {
        return toDoDao.findAll();
    }

    @GetMapping("/{id}")
    public ToDoModel getOne(@PathVariable("id") Long id) {
        ToDoModel toDoModel = toDoDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid model Id:" + id));
        return toDoModel;
    }

    @PostMapping("/create")
    public ToDoModel create(@RequestBody ToDoModel toDoModel) {
        return toDoDao.save(toDoModel);
    }

    @PutMapping("/update/{id}")
    public ToDoModel update(@PathVariable("id") Long id, @RequestBody ToDoModel toDoModel) {

        ToDoModel toDoModelFromDb = toDoDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid model Id:" + id));

        toDoModelFromDb.setTitle(toDoModel.getTitle());
        toDoModelFromDb.setDate(toDoModel.getDate());

        return toDoDao.save(toDoModelFromDb);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        ToDoModel toDoModel = toDoDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid model Id:" + id));

        toDoDao.delete(toDoModel);
    }

}
