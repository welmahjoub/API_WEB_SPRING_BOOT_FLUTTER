package com.privatenanny.privatenanny.repository;

import com.privatenanny.privatenanny.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Group repository.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    /**
     * Find group by id group.
     *
     * @param id the id
     * @return the group
     */
    @Query(value = " SELECT * FROM groups where id = ?1", nativeQuery = true)
    public Group findGroupById(Long id);

    /**
     * Find utilisateur group by name group.
     *
     * @param name    the name
     * @param ownerId the owner id
     * @return the group
     */
    @Query(value = " SELECT * FROM groups where group_name = ?1 AND group_owner_id = ?2", nativeQuery = true)
    public Group findUtilisateurGroupByName(String name, Long ownerId);

    /**
     * Find group by owner id list.
     *
     * @param ownerId the owner id
     * @return the list
     */
    @Query(value = " SELECT * FROM groups where group_owner_id = ?1", nativeQuery = true)
    public List<Group> findGroupByOwnerID(Long ownerId);

}
