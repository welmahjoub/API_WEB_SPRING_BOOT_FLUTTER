package com.privatenanny.privatenanny.service;

import com.privatenanny.privatenanny.model.Task;
import com.privatenanny.privatenanny.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Task service.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Instantiates a new Task service.
     *
     * @param taskRepository the task repository
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get all tasks list.
     *
     * @return the list
     */
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
