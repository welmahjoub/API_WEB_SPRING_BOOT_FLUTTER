package com.privatenanny.privatenanny.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Group.
 */
@Entity(name = "groups")
@Table
@Getter @Setter
@NoArgsConstructor
public class Group {

    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "group_name",
            nullable = false
    )
    private String groupName;


    @ManyToOne
    @JsonIgnoreProperties({"groups", "contacts", "taskList"})
    private Utilisateur groupOwner;

    @ManyToMany
    @JsonIgnoreProperties({"groups", "contacts", "taskList"})
    private List<Utilisateur> groupMembers;


    /**
     * Instantiates a new Group.
     *
     * @param groupName  the group name
     * @param groupOwner the group owner
     */
    public Group(String groupName, Utilisateur groupOwner) {
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        this.groupMembers = new ArrayList<>();
    }
    public Group(String groupName, Utilisateur groupOwner, List<Utilisateur> groupMembers) {
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        this.groupMembers = groupMembers;
    }
}
