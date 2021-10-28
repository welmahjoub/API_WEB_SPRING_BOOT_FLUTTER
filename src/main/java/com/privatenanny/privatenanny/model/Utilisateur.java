package com.privatenanny.privatenanny.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Utilisateur.
 */
@Entity(name = "utilisateur")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "user_pseudo_unique", columnNames = "pseudo"),
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                //@UniqueConstraint(name = "user_phone_unique", columnNames = "phone_no"),
                @UniqueConstraint(name = "user_uid", columnNames = "uid")
        }
)
@Getter @Setter
@NoArgsConstructor
public class Utilisateur {


    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name= "uid",
            nullable = false,
            updatable = false
    )
    private String uid;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "pseudo",
            nullable = false
    )
    private String pseudo;

    @Column(
            name = "display_name",
            nullable = false
    )
    private String displayName;

    @Column(
            name = "phone_no"
            //nullable = false
    )
    private String phoneNo;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Task> taskList;

    @ManyToMany
    @JsonIgnoreProperties({"contacts", "groups", "taskList"})
    private List<Utilisateur> contacts;

    @OneToMany
    private List<Group> groups;

    /**
     * Instantiates a new Utilisateur.
     *
     * @param email       the email
     * @param pseudo      the pseudo
     * @param displayName the display name
     * @param phoneNo     the phone no
     * @param uid         the uid
     */
    public Utilisateur(String email, String pseudo, String displayName, String phoneNo, String uid) {
        this.email = email;
        this.pseudo = pseudo;
        this.displayName = displayName;
        this.phoneNo = phoneNo;
        this.uid = uid;
        this.contacts = new ArrayList<>();
        this.taskList = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id.equals(that.id) && uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid);
    }
}
