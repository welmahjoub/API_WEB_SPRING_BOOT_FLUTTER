package com.privatenanny.privatenanny.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * The type Task.
 */
@Entity(name = "task")
@Table
@EnableJpaAuditing
@Getter @Setter
@NoArgsConstructor
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties( { "contacts", "taskList", "groups"})
    private Utilisateur user;


    @Column(
            name = "detail",
            updatable = false
    )
    private String detail;
    @Column(
            name = "title",
            updatable = false
    )
    private String title;

    @ManyToMany
    @JsonIgnoreProperties({"taskList", "email", "contacts", "groups"})
    private List<Utilisateur> receivers;

    private Action action;
    private Boolean repeat;
    private int repeatitionNumber;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "validated_at")
    private Date validatedAt;

    @Column(name = "is_validated")
    private Boolean isValidated;

    @Column(name = "delay_between_repetition")
    private Long delayBetweenRepetition;

    @Column(name = "date_time")
    private Date dateTime;

    /**
     * Instantiates a new Task.
     *
     * @param user                   the user
     * @param detail                 the detail
     * @param title                  the title
     * @param receivers              the receivers
     * @param action                 the action
     * @param repeat                 the repeat
     * @param repeatitionNumber      the repeatition number
     * @param delayBetweenRepetition the delay between repetition
     * @param dateTime               the date time
     */
    public Task(Utilisateur user, String detail, String title, List<Utilisateur> receivers, Action action, Boolean repeat, int repeatitionNumber, Long delayBetweenRepetition, Date dateTime) {
        this.user = user;
        this.detail = detail;
        this.title = title;
        this.receivers = receivers;
        this.action = action;
        this.repeat = repeat;
        this.repeatitionNumber = repeatitionNumber;
        this.delayBetweenRepetition = delayBetweenRepetition;
        this.dateTime = dateTime;
        this.createdAt = new Date();
        this.isValidated = false;
    }

    /**
     * The enum Action.
     */
    public enum Action {
        /**
         * Notification action.
         */
        NOTIFICATION,
        /**
         * Sms action.
         */
        SMS,
        /**
         * Call action.
         */
        CALL }
}
