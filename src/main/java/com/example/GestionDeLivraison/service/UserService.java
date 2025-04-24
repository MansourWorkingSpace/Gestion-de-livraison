package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.AvisLivreur;
import com.example.GestionDeLivraison.Model.Livreur;
import com.example.GestionDeLivraison.Model.User;

import java.util.List;

public interface UserService {
    byte[] getImageById(Integer idUser);
    User getUserById(Integer idUser);
    User updateUserInfo(Integer idUser, String nom, String prenom, Integer age, String tlf, String email);
    List<AvisLivreur> getAllAvisForLivreur(Integer id_l);
    Livreur updateTarifRetour(Integer id_l, double tarif_retour);
    Livreur updateTarifLivraison(Integer id_l, double tarif_livraison);
    AvisLivreur addAvisToLivreur(Integer id_l, Integer idUser, String message);
    boolean emailExists(String email);
    void saveUser(User user);
    User getUserByEmail(String email);
    public boolean verifyPassword(String rawPassword, String storedPassword);
}
