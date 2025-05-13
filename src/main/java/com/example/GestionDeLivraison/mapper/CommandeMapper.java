package com.example.GestionDeLivraison.mapper;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.dto.CommandeDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {

    // Convertit un objet Commande en CommandeDTO
    public CommandeDTO toDTO(Commande commande) {
        if (commande == null) {
            return null;  // Retourne null si l'objet commande est null
        }
        return new CommandeDTO(commande);  // Utilise le constructeur de CommandeDTO qui prend un objet Commande
    }
}
