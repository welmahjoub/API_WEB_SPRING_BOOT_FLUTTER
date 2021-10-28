package com.privatenanny.privatenanny.service;

import com.privatenanny.privatenanny.model.Group;
import com.privatenanny.privatenanny.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Group service.
 */
@Service
public class GroupService  {

    private final GroupRepository groupRepository;


    /**
     * Instantiates a new Group service.
     *
     * @param groupRepository the group repository
     */
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * Get all groups list.
     *
     * @return the list
     */
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }
}
