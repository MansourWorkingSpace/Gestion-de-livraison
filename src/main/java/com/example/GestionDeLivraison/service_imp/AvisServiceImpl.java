package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.AvisProduit;
import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.Model.Produit;
import com.example.GestionDeLivraison.service.AvisService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AvisServiceImpl implements AvisService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AvisProduit createAvis(AvisProduit avis) {
        Client client = entityManager.find(Client.class, avis.getClient().getIdUser());
        Produit produit = entityManager.find(Produit.class, avis.getProduit().getIdProd());

        if (client == null) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + avis.getClient().getIdUser());
        }
        if (produit == null) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + avis.getProduit().getIdProd());
        }

        avis.setClient(client);
        avis.setProduit(produit);
        return entityManager.merge(avis);
    }
}