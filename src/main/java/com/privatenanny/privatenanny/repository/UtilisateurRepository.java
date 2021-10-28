package com.privatenanny.privatenanny.repository;

import com.privatenanny.privatenanny.model.Group;
import com.privatenanny.privatenanny.model.Utilisateur;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Utilisateur repository.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Find utilisateur by id utilisateur.
     *
     * @param id the id
     * @return the utilisateur
     */
    Utilisateur findUtilisateurById(Long id);

    /**
     * Find utilisateur by pseudo utilisateur.
     *
     * @param pseudo the pseudo
     * @return the utilisateur
     */
    Utilisateur findUtilisateurByPseudo(String pseudo);

    /**
     * Find utilisateur by phone no utilisateur.
     *
     * @param phoneNo the phone no
     * @return the utilisateur
     */
    Utilisateur findUtilisateurByPhoneNo(String phoneNo);

    /**
     * Find utilisateur by email utilisateur.
     *
     * @param email the email
     * @return the utilisateur
     */
    Utilisateur findUtilisateurByEmail(String email);

    /**
     * Find utilisateur by uid utilisateur.
     *
     * @param uid the uid
     * @return the utilisateur
     */
    Utilisateur findUtilisateurByUid(String uid);

    /**
     * Find utilisateur groups list.
     *
     * @param id the id
     * @return the list
     */
    @Query(value = "SELECT * from groups where group_owner_id = ?1", nativeQuery = true)
    public List<Group> findUtilisateurGroups(Long id);

    /**
     * Find utilisateur contacts list.
     *
     * @param id the id
     * @return the list
     */
    @Query(value = "SELECT contacts_id from utilisateur_contacts where utilisateur_id = ?1", nativeQuery = true)
    public List<Long> findUtilisateurContacts(Long id);

}
