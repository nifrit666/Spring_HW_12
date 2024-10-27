package ru.geekbrains.springHW5.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springHW5.model.Task;
import ru.geekbrains.springHW5.model.TaskStatus;
import ru.geekbrains.springHW5.service.FileGateway;
import ru.geekbrains.springHW5.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final FileGateway fileGateway;


    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        task.setCreatedDate(LocalDateTime.now());
        fileGateway.writeToFile(task.getTask_name() + ".txt", task.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


    @GetMapping("/status/{status}")
    public List<Task> findByStatus(@PathVariable TaskStatus status){
        return taskService.findByStatus(status);
    }


    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskStatus(id,task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}