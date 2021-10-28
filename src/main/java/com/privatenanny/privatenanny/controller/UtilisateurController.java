package com.privatenanny.privatenanny.controller;

import com.privatenanny.privatenanny.model.Group;
import com.privatenanny.privatenanny.model.Task;
import com.privatenanny.privatenanny.model.Utilisateur;
import com.privatenanny.privatenanny.repository.GroupRepository;
import com.privatenanny.privatenanny.repository.TaskRepository;
import com.privatenanny.privatenanny.repository.UtilisateurRepository;
import com.privatenanny.privatenanny.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The type Utilisateur controller.
 */
@RestController
@RequestMapping (path = "/user")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;

    /**
     * Instantiates a new Utilisateur controller.
     *
     * @param utilisateurService    the utilisateur service
     * @param utilisateurRepository the utilisateur repository
     * @param groupRepository       the group repository
     * @param taskRepository        the task repository
     */
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurRepository utilisateurRepository, GroupRepository groupRepository, TaskRepository taskRepository) {
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
        this.groupRepository=groupRepository;
        this.taskRepository = taskRepository;
    }

    //@GET MAPPING

    /**
     * Get all utilisateur list.
     *
     * @return the list
     */
    @GetMapping("/")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateur();
    }

    /**
     * Get utilisateur by uid utilisateur.
     *
     * @param id the id
     * @return the utilisateur
     */
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurByUid(@PathVariable String id){
        return utilisateurRepository.findUtilisateurByUid(id);
    }

    /**
     * Get utilisateur by pseudo utilisateur.
     *
     * @param pseudo the pseudo
     * @return the utilisateur
     */
    @GetMapping("/findUserByPseudo/{pseudo}")
    public Utilisateur getUtilisateurByPseudo(@PathVariable String pseudo){
        return utilisateurRepository.findUtilisateurByPseudo(pseudo);
    }

    /**
     * Get utilisateur by email utilisateur.
     *
     * @param email the email
     * @return the utilisateur
     */
    @GetMapping("/findUserByEmail/{email}")
    public Utilisateur getUtilisateurByEmail(@PathVariable String email){
        return utilisateurRepository.findUtilisateurByEmail(email);
    }

    /**
     * Get utilisateur by phone no utilisateur.
     *
     * @param phoneNo the phone no
     * @return the utilisateur
     */
    @GetMapping("/findUserByPhoneNo/{phoneNo}")
    public Utilisateur getUtilisateurByPhoneNo(@PathVariable String phoneNo){
        return utilisateurRepository.findUtilisateurByPhoneNo(phoneNo);
    }

    /**
     * Get utilisateur groups list list.
     *
     * @param id the id
     * @return the list
     */
    @GetMapping("/{id}/groups")
    public List<Group> getUtilisateurGroupsList(@PathVariable Long id){
        return utilisateurRepository.findUtilisateurById(id).getGroups();
    }



    //@POST MAPPING

    /**
     * Save utilisateur response entity.
     *
     * @param utilisateur the utilisateur
     * @return the response entity
     */
    @PostMapping(path = "/")
    public ResponseEntity<Utilisateur> saveUtilisateur(@RequestBody Utilisateur utilisateur){
        try {
            Utilisateur _utilisateur = utilisateurRepository.save(new Utilisateur(
                    utilisateur.getEmail(),
                    utilisateur.getPseudo(),
                    utilisateur.getDisplayName(),
                    utilisateur.getPhoneNo(),
                    utilisateur.getUid())
            );
            return new ResponseEntity<>(_utilisateur, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/")
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur){
        try {
            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(utilisateur.getUid());
            _utilisateur.setDisplayName(utilisateur.getDisplayName());
            _utilisateur.setPseudo(utilisateur.getPseudo());
            _utilisateur.setPhoneNo(utilisateur.getPhoneNo());

            utilisateurRepository.save(_utilisateur);
            return new ResponseEntity<>(_utilisateur, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add user group response entity.
     *
     * @param uid   the uid
     * @param group the group
     * @return the response entity
     */
    @PostMapping(path="/{uid}/groups")
    public ResponseEntity<Utilisateur> addUserGroup(@PathVariable String uid, @RequestBody Group group){
        try {

            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(uid);
            Group _group = groupRepository.save(new Group(group.getGroupName(), _utilisateur));
            Group savedGroup = groupRepository.save(_group);
            _utilisateur.getGroups().add(savedGroup);
            return new ResponseEntity<>(_utilisateur, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add user task response entity.
     *
     * @param uid  the uid
     * @param task the task
     * @return the response entity
     */
    @PostMapping(path = "/{uid}/tasks")
    public ResponseEntity<Utilisateur> addUserTask(@PathVariable String uid, @RequestBody Task task){
        try {

            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(uid);
            Task _task = taskRepository.save( new Task(_utilisateur, task.getDetail(), task.getTitle(), task.getReceivers(),
                    task.getAction(), task.getRepeat(), task.getRepeatitionNumber(), task.getDelayBetweenRepetition(),
                    task.getDateTime()));
            _utilisateur.getTaskList().add(_task);

            return new ResponseEntity<>(_utilisateur, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Add contact to user list response entity.
     *
     * @param uid     the uid
     * @param contact the contact
     * @return the response entity
     */
    @PostMapping("/{uid}/contacts")
    public ResponseEntity<Utilisateur> addContactToUserList(@PathVariable String uid, @RequestBody Utilisateur contact){
        try {

            Utilisateur _utilisateur = utilisateurRepository.findUtilisateurByUid(uid);
            Utilisateur _contact = utilisateurRepository.findUtilisateurByUid(contact.getUid());
            _utilisateur.getContacts().add(_contact);
            utilisateurRepository.save(_utilisateur);

            return new ResponseEntity<>(_utilisateur, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
