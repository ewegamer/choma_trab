package com.example.demo.services;

import com.example.demo.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAll(String status) {
        return taskRepository.findByStatus(status);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        if(task.getStatus() == null) task.setStatus("To do");
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDeadLine(updatedTask.getDeadLine());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    public void moveTask(Long id, String newStatus) {
        Task task = findById(id);
        if (task != null && isValidStatusTransition(task.getStatus(), newStatus)) {
            task.setStatus(newStatus);
            taskRepository.save(task);
        }
    }

    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        if (currentStatus.equals("To Do") && newStatus.equals("In Progress"))
            return true;
        if (currentStatus.equals("In Progress") && newStatus.equals("Done"))
            return true;
        return false;
    }
}
