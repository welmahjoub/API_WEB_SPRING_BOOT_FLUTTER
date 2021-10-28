package com.privatenanny.privatenanny.repository;

import com.privatenanny.privatenanny.model.Task;
import com.privatenanny.privatenanny.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Find task by id task.
     *
     * @param id the id
     * @return the task
     */
    @Query(value = " SELECT * FROM task where id = ?1", nativeQuery = true)
    public Task findTaskById(Long id);

    /**
     * Find tasks by title task.
     *
     * @param title the title
     * @return the task
     */
    @Query(value = " SELECT * FROM task where title = ?1", nativeQuery = true)
    public Task findTasksByTitle(String title);

    /**
     * Find tasks by detail task.
     *
     * @param detail the detail
     * @return the task
     */
    @Query(value = " SELECT * FROM task where detail = ?1", nativeQuery = true)
    public Task findTasksByDetail(String detail);

    /**
     * Find tasks by user task.
     *
     * @param user the user
     * @return the task
     */
    @Query(value = " SELECT * FROM task where user = ?1", nativeQuery = true)
    public Task findTasksByUser(Utilisateur user);

    /**
     * Find tasks by created at task.
     *
     * @param createdAt the created at
     * @return the task
     */
    @Query(value = " SELECT * FROM task where created_at = ?1", nativeQuery = true)
    public Task findTasksByCreatedAt(Date createdAt);

    /**
     * Find tasks by validated at task.
     *
     * @param validatedAt the validated at
     * @return the task
     */
    @Query(value = " SELECT * FROM task where validatedAt = ?1", nativeQuery = true)
    public Task findTasksByValidatedAt(Date validatedAt);

    /**
     * Find tasks by delay between repetition task.
     *
     * @param delayBetweenRepetition the delay between repetition
     * @return the task
     */
    @Query(value = " SELECT * FROM task where delay_between_repetition = ?1", nativeQuery = true)
    public Task findTasksByDelayBetweenRepetition(Timestamp delayBetweenRepetition);

    /**
     * Find tasks by date time task.
     *
     * @param dateTime the date time
     * @return the task
     */
    @Query(value = " SELECT * FROM task where date_time = ?1", nativeQuery = true)
    public Task findTasksByDateTime(Timestamp dateTime);


}
