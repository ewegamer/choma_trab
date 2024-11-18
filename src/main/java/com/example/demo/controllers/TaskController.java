package com.example.demo.controllers;

import com.example.demo.entities.Task;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.findAll(status);
    }

    @GetMapping("/priority")
    public List<Task> getTasksByPriority() {
        return taskService.orderByPriority();
    }

    @GetMapping("/filter")
    public List<Task> filterTasks(@RequestParam String priority, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return taskService.filterTasks(priority, localDate);
    }

    @GetMapping("/report")
    public String generateReport() {
        return taskService.generateReport();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PutMapping("/{id}/move/{status}")
    public void moveTask(@PathVariable Long id, @PathVariable String status) {
        taskService.moveTask(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
