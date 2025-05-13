package com.example.GestionDeLivraison.service_imp;
import com.example.GestionDeLivraison.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.GestionDeLivraison.repository.ProduitRepository;
import com.example.GestionDeLivraison.repository.CommandeRepository;

import com.example.GestionDeLivraison.Model.*;
import com.example.GestionDeLivraison.dto.CommandeDTO;
import com.example.GestionDeLivraison.service.DeliveryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    private CommandeRepository commandeRepository;


    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    @Transactional
    public Commande updateCommandeStatus(Integer id, String statut) {
        Commande commande = entityManager.find(Commande.class, id);
        if (commande != null) {
            try {
                commande.setStatut(StatutCommande.valueOf(statut));
                return entityManager.merge(commande);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Statut invalide: " + statut);
            }
        }
        throw new RuntimeException("Commande non trouvée avec l'ID: " + id);
    }

    @Override
    @Transactional
    public Commande assignLivreur(Integer idCmd, Integer idLivreur) {
        Commande commande = entityManager.find(Commande.class, idCmd);
        Livreur livreur = entityManager.find(Livreur.class, idLivreur);

        if (commande == null) {
            throw new RuntimeException("Commande non trouvée avec l'ID: " + idCmd);
        }
        if (livreur == null) {
            throw new RuntimeException("Livreur non trouvé avec l'ID: " + idLivreur);
        }

        Long existingAssignment = entityManager.createQuery(
                        "SELECT COUNT(d) FROM DashboardL d WHERE d.commande.idCmd = :idCmd",
                        Long.class
                )
                .setParameter("idCmd", idCmd)
                .getSingleResult();

        if (existingAssignment > 0) {
            throw new RuntimeException("La commande est déjà assignée à un livreur");
        }

        DashboardL dashboardL = new DashboardL();
        dashboardL.setCommande(commande);
        dashboardL.setLivreur(livreur);

        if (livreur.getCommandesLivrees() != null) {
            livreur.getCommandesLivrees().add(dashboardL);
        }
        entityManager.persist(dashboardL);

        return commande;
    }


    @Override
    public Map<String, Object> getFilteredOrders(int page, int size, String status, String searchTerm, String date, String agent, String sortBy, String sortDirection) {
        StringBuilder queryString = new StringBuilder(
                "SELECT c FROM Commande c " +
                        "LEFT JOIN FETCH c.client " +
                        "LEFT JOIN FETCH c.produit " +
                        "LEFT JOIN FETCH c.dashboardL d " +
                        "WHERE 1=1"
        );

        if (!"all".equalsIgnoreCase(status)) {
            queryString.append(" AND c.statut = :status");
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            queryString.append(" AND (c.client.nom LIKE :searchTerm OR c.client.prenom LIKE :searchTerm)");
        }
        if (date != null && !date.isEmpty()) {
            queryString.append(" AND CAST(c.dateCmd AS string) LIKE :date");
        }
        if (!"all".equalsIgnoreCase(agent)) {
            queryString.append(" AND d.livreur.idUser = :agent");
        }

        queryString.append(" ORDER BY c.").append(sortBy).append(" ").append(sortDirection);

        TypedQuery<Commande> query = entityManager.createQuery(queryString.toString(), Commande.class);

        if (!"all".equalsIgnoreCase(status)) {
            query.setParameter("status", StatutCommande.valueOf(status));
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.setParameter("searchTerm", "%" + searchTerm + "%");
        }
        if (date != null && !date.isEmpty()) {
            query.setParameter("date", "%" + date + "%");
        }
        if (!"all".equalsIgnoreCase(agent)) {
            query.setParameter("agent", Integer.parseInt(agent));
        }

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        List<Commande> commandes = query.getResultList();
        List<CommandeDTO> commandeDTOs = commandes.stream()
                .map(CommandeDTO::new)
                .collect(Collectors.toList());

        StringBuilder countQueryString = new StringBuilder("SELECT COUNT(c) FROM Commande c WHERE 1=1");

        if (!"all".equalsIgnoreCase(status)) {
            countQueryString.append(" AND c.statut = :status");
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            countQueryString.append(" AND (c.client.nom LIKE :searchTerm OR c.client.prenom LIKE :searchTerm)");
        }
        if (date != null && !date.isEmpty()) {
            countQueryString.append(" AND CAST(c.dateCmd AS string) LIKE :date");
        }
        if (!"all".equalsIgnoreCase(agent)) {
            countQueryString.append(" AND c.dashboardL.livreur.idUser = :agent");
        }

        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryString.toString(), Long.class);

        if (!"all".equalsIgnoreCase(status)) {
            countQuery.setParameter("status", StatutCommande.valueOf(status));
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            countQuery.setParameter("searchTerm", "%" + searchTerm + "%");
        }
        if (date != null && !date.isEmpty()) {
            countQuery.setParameter("date", "%" + date + "%");
        }
        if (!"all".equalsIgnoreCase(agent)) {
            countQuery.setParameter("agent", Integer.parseInt(agent));
        }

        long totalItems = countQuery.getSingleResult();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        Map<String, Object> response = new HashMap<>();
        response.put("commandes", commandeDTOs);        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);

        return response;
    }



}