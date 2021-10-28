package com.privatenanny.privatenanny.controller;

import com.privatenanny.privatenanny.model.Note;
import com.privatenanny.privatenanny.model.Task;
import com.privatenanny.privatenanny.model.Utilisateur;
import com.privatenanny.privatenanny.repository.TaskRepository;
import com.privatenanny.privatenanny.repository.UtilisateurRepository;
import com.privatenanny.privatenanny.service.NotificationService;
import com.privatenanny.privatenanny.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Task controller.
 */
@RestController
@RequestMapping (path = "/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final NotificationService notificationService;

    /**
     * Instantiates a new Task controller.
     *
     * @param taskService           the task service
     * @param utilisateurRepository the utilisateur repository
     * @param taskRepository        the task repository
     * @param notificationService   the notification service
     */
    @Autowired
    public TaskController(TaskService taskService, UtilisateurRepository utilisateurRepository,
                          TaskRepository taskRepository, NotificationService notificationService) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.utilisateurRepository =utilisateurRepository;
        this.notificationService =notificationService;
    }

    //@GET MAPPING

    /**
     * Get all task list.
     *
     * @return the list
     */
    @GetMapping("/")
    public List<Task> getAllTask(){
        return taskService.getAllTasks();
    }

    /**
     * Get task by id task.
     *
     * @param id the id
     * @return the task
     */
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskRepository.findTaskById(id);
    }

    /**
     * Get task by user task.
     *
     * @param user the user
     * @return the task
     */
    @GetMapping("/findTasksByUser/{user}")
    public Task getTaskByUser(@PathVariable Utilisateur user){
        return taskRepository.findTasksByUser(user);
    }

    @GetMapping("/findTasksByReceiver/{uid}")
    public List<Task> getTaskByReceiver(@PathVariable String uid){

        Utilisateur user = utilisateurRepository.findUtilisateurByUid(uid);
        List<Task> tasks = taskRepository.findAll();
        List<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getReceivers().contains(user))
                result.add(t);
        }

        return result;
    }

    /**
     * Get task by created at task.
     *
     * @param createdAt the created at
     * @return the task
     */
    @GetMapping("/findTasksByCreatedAt/{createdAt}")
    public Task getTaskByCreatedAt(@PathVariable Date createdAt){
        return taskRepository.findTasksByCreatedAt(createdAt);
    }

    /**
     * Get task by validated at task.
     *
     * @param validatedAt the validated at
     * @return the task
     */
    @GetMapping("/findTasksByValidatedAt/{validatedAt}")
    public Task getTaskByValidatedAt(@PathVariable Date validatedAt){
        return taskRepository.findTasksByValidatedAt(validatedAt);
    }

    /**
     * Get task by delay between repetition task.
     *
     * @param delayBetweenRepetition the delay between repetition
     * @return the task
     */
    @GetMapping("/findTasksByDelayBetweenRepetition/{delayBetweenRepetition}")
    public Task getTaskByDelayBetweenRepetition(@PathVariable Timestamp delayBetweenRepetition){
        return taskRepository.findTasksByDelayBetweenRepetition(delayBetweenRepetition);
    }

    /**
     * Get task by date time task.
     *
     * @param dateTime the date time
     * @return the task
     */
    @GetMapping("/findTasksByDateTime/{dateTime}")
    public Task getTaskByDateTime(@PathVariable Timestamp dateTime){
        return taskRepository.findTasksByDateTime(dateTime);
    }


    //@POST MAPPING

    /**
     * Save task response entity.
     *
     * @param task the task
     * @return the response entity
     */
    @PostMapping(path = "/")
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
        try {
            Utilisateur _userCreator = utilisateurRepository.findUtilisateurByUid(task.getUser().getUid());
            Task _task = taskRepository.save(new Task(
                    _userCreator,
                    task.getDetail(),
                    task.getTitle(),
                    task.getReceivers(),
                    task.getAction(),
                    task.getRepeat(),
                    task.getRepeatitionNumber(),
                    task.getDelayBetweenRepetition(),
                    task.getDateTime()));

            Note _note = new Note( _userCreator.getDisplayName() + " vous a laissé une note",
                    _task.getTitle(), _task.getReceivers());

            notificationService.sendNotification(_note);
            return new ResponseEntity<>(_task, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update task response entity for validation
     *
     * @param task the task
     * @return the response entity
     */
    @PutMapping(path = "/")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        try {

            Task _task = taskRepository.findTaskById(task.getId());

            _task.setIsValidated(task.getIsValidated());
            _task.setValidatedAt(task.getValidatedAt());

            taskRepository.save(_task);
            return new ResponseEntity<>(_task, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete task response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id){

        try {
            taskRepository.deleteById(id);

            return new ResponseEntity<>(id, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}

