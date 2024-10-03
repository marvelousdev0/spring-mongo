package com.vamshimaddur.mongodb.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    Task getTaskById(@PathVariable String id) {
        return taskRepository.findById(id).orElse(null);
    }

    @GetMapping("/status/{status}")
    List<Task> getTasksByStatus(@PathVariable String status) {
        return taskRepository.findByStatus(Status.valueOf(status.toUpperCase()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateTask(@PathVariable String id, @RequestBody Task task) {
        taskRepository.findById(id)
                .map(existingTask -> {
                    taskRepository.save(task);
                    return existingTask;
                });
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }
}
