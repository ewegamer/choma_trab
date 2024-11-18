package com.example.demo.repository;

import com.example.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);

    List<Task> findByPriority(String priority);

    List<Task> findByDeadLineBeforeAndStatusNot(LocalDate deadLine, String status);

    List<Task> findAllByOrderByPriorityOrderAsc();
}
