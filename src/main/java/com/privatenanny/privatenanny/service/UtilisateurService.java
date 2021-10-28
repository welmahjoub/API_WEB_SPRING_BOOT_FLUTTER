package com.privatenanny.privatenanny.service;

import com.privatenanny.privatenanny.model.Utilisateur;
import com.privatenanny.privatenanny.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Utilisateur service.
 */
@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;


    /**
     * Instantiates a new Utilisateur service.
     *
     * @param utilisateurRepository the utilisateur repository
     */
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Get all utilisateur list.
     *
     * @return the list
     */
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurRepository.findAll();
    }
}
