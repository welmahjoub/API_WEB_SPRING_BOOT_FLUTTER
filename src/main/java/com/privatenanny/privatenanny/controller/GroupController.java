package com.privatenanny.privatenanny.controller;


import com.privatenanny.privatenanny.model.Group;
import com.privatenanny.privatenanny.model.Utilisateur;
import com.privatenanny.privatenanny.repository.GroupRepository;
import com.privatenanny.privatenanny.repository.UtilisateurRepository;
import com.privatenanny.privatenanny.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Group controller.
 */
@RestController
@RequestMapping(path = "/group")
public class GroupController {


    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final UtilisateurRepository utilisateurRepository;

    /**
     * Instantiates a new Group controller.
     *  @param groupService    the group service
     * @param groupRepository the group repository
     * @param utilisateurRepository
     */
    @Autowired
    public GroupController(GroupService groupService, GroupRepository groupRepository, UtilisateurRepository utilisateurRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
        this.utilisateurRepository = utilisateurRepository;
    }


    /**
     * Get all list.
     *
     * @return the list
     */
    @GetMapping(path = "/")
    public List<Group> getAll(){
        return groupService.getAllGroups();
    }

    /**
     * Get group by id group.
     *
     * @param id the id
     * @return the group
     */
    @GetMapping(path = "/{id}")
    public Group getGroupById(@PathVariable Long id){ return groupRepository.findGroupById(id); }

    /**
     * Gets group by owner id.
     *
     * @param id the id
     * @return the group by owner id
     */
    @GetMapping(path = "/getGroupListByOwnerId/{id}")
    public List<Group> getGroupByOwnerId(@PathVariable Long id) { return groupRepository.findGroupByOwnerID(id);}

    /**
     * Gets utilisateur group by name.
     *
     * @param name the name
     * @param id   the id
     * @return the utilisateur group by name
     */
    @GetMapping(path = "/{id}/{name}")
    public Group getUtilisateurGroupByName(@PathVariable String name, @PathVariable Long id) { return groupRepository.findUtilisateurGroupByName(name, id);}

    /**
     * Save group response entity.
     *
     * @param group the group
     * @return the response entity
     */
    @PostMapping(path = "/")
    public ResponseEntity<Group> saveGroup(@RequestBody Group group) {
        try {
            //création du groupe
            Group _group = groupRepository.save(new Group(
                    group.getGroupName(),
                    group.getGroupOwner(),
                    group.getGroupMembers()
            ));

            //ajout groupe utilisateur
            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(group.getGroupOwner().getUid());

            _utilisateur.getGroups().add(_group);
            utilisateurRepository.save(_utilisateur);

            return new ResponseEntity<>(_group, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path ="/")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        try {
            //modification group
            Group _group =groupRepository.findGroupById(group.getId());

            _group.setGroupName(group.getGroupName());
            _group.setGroupOwner(group.getGroupOwner());
            _group.setGroupMembers((group.getGroupMembers()));

            groupRepository.save(_group);

            //pas besoin de update l'utilisateur

            return new ResponseEntity<>(group, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<Group> addUserToGroup(@PathVariable Long id, @RequestBody Utilisateur user){
        try{
            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(user.getUid());
            Group _group = groupRepository.findGroupById(id);

            _group.getGroupMembers().add(_utilisateur);
            _utilisateur.getGroups().add(_group);

            groupRepository.save(_group);
            utilisateurRepository.save(_utilisateur);

            return new ResponseEntity<>(_group, HttpStatus.OK);

        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
