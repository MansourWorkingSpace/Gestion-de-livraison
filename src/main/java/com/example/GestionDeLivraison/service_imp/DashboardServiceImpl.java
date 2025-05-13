package com.example.GestionDeLivraison.service_imp;
import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.dto.ClientDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.Model.StatutCommande;
import com.example.GestionDeLivraison.dto.CommandeDTO;
import com.example.GestionDeLivraison.dto.CommandeSummaryDTO;
import com.example.GestionDeLivraison.service.DashboardService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class
DashboardServiceImpl implements DashboardService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CommandeSummaryDTO> getRecentCommandes(int page, int size) {
        Query query = entityManager.createNativeQuery(
                "SELECT " +
                        "    c.id_cmd, " +
                        "    u.nom AS clientNom, " +
                        "    u.prenom AS clientPrenom, " +
                        "    p.nom_Prod, " +
                        "    lu.nom AS livreurNom, " +
                        "    lu.prenom AS livreurPrenom, " +
                        "    c.statut, " +
                        "    c.date_cmd, " +
                        "    c.prix_totale " +
                        "FROM commande c " +
                        "LEFT JOIN client cl ON c.id_clt = cl.id_user " +
                        "LEFT JOIN user u ON cl.id_user = u.id_user " +
                        "LEFT JOIN produit p ON c.id_prod = p.id_prod " +
                        "LEFT JOIN dashboardL d ON d.id_cmd = c.id_cmd " +
                        "LEFT JOIN livreur l ON d.id_l = l.id_user " +
                        "LEFT JOIN user lu ON l.id_user = lu.id_user " +
                        "WHERE c.statut IS NOT NULL " +
                        "AND u.id_user IS NOT NULL " +
                        "ORDER BY c.date_cmd DESC " +
                        "LIMIT :size OFFSET :offset"
        );
        query.setParameter("size", size);
        query.setParameter("offset", page * size);

        List<Object[]> results = query.getResultList();

        final String MSG_INDISPONIBLE = "non disponible";

        List<CommandeSummaryDTO> commandeDTOs = results.stream()
                .map(row -> new CommandeSummaryDTO(
                        (Integer) row[0],
                        row[1] != null && row[2] != null ? row[1] + " " + row[2] : "Le nom du client n'est pas disponible",
                        row[4] != null && row[5] != null ? row[4] + " " + row[5] : "Non attribué",
                        row[6] != null ? (String) row[6] : null,
                        row[7] != null ? LocalDateTime.ofInstant(((java.sql.Timestamp) row[7]).toInstant(), ZoneId.systemDefault()) : null,
                        row[8] != null ? (Double) row[8] : null
                ))
                .collect(Collectors.toList());

        return commandeDTOs;
    }
    @Override
    public List<ClientDTO> getTopClients(int page, int size) {
        List<Client> clients = entityManager.createQuery(
                        "SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.commandes cmd " +
                                "ORDER BY SIZE(c.commandes) DESC",
                        Client.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        return clients.stream().map(client -> {
            List<CommandeDTO> commandesDTO = client.getCommandes().stream()
                    .map(CommandeDTO::new)
                    .collect(Collectors.toList());

            return new ClientDTO(
                    client.getIdUser(),
                    client.getNom(),
                    client.getPrenom(),
                    client.getEmail(),
                    client.getTlf(),
                    client.getAdresse(),
                    client.getCodePostale(),
                    client.getStatut() != null ? client.getStatut().toString() : "client",
                    commandesDTO
            );
        }).collect(Collectors.toList());
    }


    @Override
    public Object updateCommandeStatus(Integer id, String statut) {
        Commande commande = entityManager.find(Commande.class, id);

        if (commande == null) {
            throw new RuntimeException("La commande n'existe pas avec l'identifiant : " + id);
        }

        if (statut == null) {
            throw new IllegalArgumentException("Le statut de la commande est requis.");
        }

        try {
            String statutNormalise = statut.toLowerCase();

            if (statutNormalise.equals("livré")) {
                commande.setStatut(StatutCommande.livré);
            } else if (statutNormalise.equals("en_attente")) {
                commande.setStatut(StatutCommande.en_attente);
            } else if (statutNormalise.equals("annulé")) {
                commande.setStatut(StatutCommande.annulé);
            } else {
                throw new IllegalArgumentException("Statut invalide : " + statut);
            }

            entityManager.merge(commande);
            return commande;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Statut invalide : " + statut);
        }
    }

    @Override
    public List<Map<String, Object>> getStatsCards() {
        Long totalLivraisons = entityManager.createQuery(
                        "SELECT COUNT(d) FROM DashboardL d", Long.class)
                .getSingleResult();

        Long totalCommandes = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Commande c", Long.class)
                .getSingleResult();

        Double revenuTotal = entityManager.createQuery(
                        "SELECT COALESCE(SUM(c.prixTotale), 0) FROM Commande c", Double.class)
                .getSingleResult();

        List<Map<String, Object>> cartes = new ArrayList<>();

        cartes.add(createCard("Total des livraisons", totalLivraisons.toString(), "truck",
                "#4CAF50", "#E8F5E9", "#2E7D32"));

        cartes.add(createCard("Total des commandes", totalCommandes.toString(), "shopping-cart",
                "#2196F3", "#E3F2FD", "#1565C0"));

        cartes.add(createCard("Revenu total", String.format("%.2f TND", revenuTotal), "money-bill-wave",
                "#FF9800", "#FFF3E0", "#EF6C00"));

        return cartes;
    }
    @Override
    public Map<String, Object> getMonthlyStats() {
        ZonedDateTime startOfMonth = ZonedDateTime.now(ZoneId.systemDefault())
                .withDayOfMonth(1)
                .truncatedTo(ChronoUnit.DAYS);

        Date startOfMonthDate = Date.from(startOfMonth.toInstant());

        Long commandesCeMois = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Commande c WHERE c.dateCmd >= :startOfMonth", Long.class)
                .setParameter("startOfMonth", startOfMonthDate)
                .getSingleResult();

        Double revenuCeMois = entityManager.createQuery(
                        "SELECT COALESCE(SUM(c.prixTotale), 0) FROM Commande c WHERE c.dateCmd >= :startOfMonth", Double.class)
                .setParameter("startOfMonth", startOfMonthDate)
                .getSingleResult();

        Map<String, Object> stats = new HashMap<>();
        stats.put("commandesCeMois", commandesCeMois);
        stats.put("revenuCeMois", revenuCeMois);

        return stats;
    }

    private Map<String, Object> createCard(String titre, String valeur, String icone,
                                           String couleur, String couleurFond, String couleurIcone) {
        Map<String, Object> carte = new LinkedHashMap<>();
        carte.put("title", titre);
        carte.put("value", valeur);
        carte.put("icon", icone);
        carte.put("color", couleur);
        carte.put("backgroundColor", couleurFond);
        carte.put("iconColor", couleurIcone);
        carte.put("iconBg", couleur);
        return carte;
    }


    @Override
    public Map<String, Object> getDeliveryStats() {
        Long totalCommandes = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Commande c", Long.class)
                .getSingleResult();

        String jpql = "SELECT c.statut, COUNT(c) FROM Commande c WHERE c.statut IS NOT NULL GROUP BY c.statut";
        List<Object[]> resultats = entityManager.createQuery(jpql, Object[].class).getResultList();

        Map<String, Object> statistiques = new HashMap<>();

        statistiques.put("LIVRÉ", createDeliveryStat(0L, 0.0, "#4CAF50"));
        statistiques.put("EN_ATTENTE", createDeliveryStat(0L, 0.0, "#FFC107"));
        statistiques.put("ANNULÉ", createDeliveryStat(0L, 0.0, "#F44336"));

        for (Object[] resultat : resultats) {
            StatutCommande statutObj = (StatutCommande) resultat[0];
            if (statutObj == null) continue;

            String statut = statutObj.name();
            Long count = (Long) resultat[1];
            double pourcentage = totalCommandes > 0 ? (count * 100.0) / totalCommandes : 0;

            statistiques.put(statut, createDeliveryStat(count, pourcentage,
                    switch (statut) {
                        case "LIVRÉ" -> "#4CAF50";
                        case "EN_ATTENTE" -> "#FFC107";
                        case "ANNULÉ" -> "#F44336";
                        default -> "#9E9E9E";
                    }));
        }

        return statistiques;
    }

    private Map<String, Object> createDeliveryStat(Long nombre, double pourcentage, String couleur) {
        Map<String, Object> stat = new HashMap<>();
        stat.put("count", nombre);
        stat.put("percentage", pourcentage);
        stat.put("color", couleur);
        return stat;
    }

    @Override
    public Map<String, Object> getDeliveryZones() {
        List<Commande> commandes = entityManager.createQuery(
                        "SELECT c FROM Commande c LEFT JOIN FETCH c.client WHERE c.adresse IS NOT NULL AND c.codePostale IS NOT NULL",
                        Commande.class)
                .getResultList();

        System.out.println("Commandes récupérées pour les zones de livraison : " + commandes.size());

        Map<String, Map<String, Object>> cityMap = new HashMap<>();

        for (Commande cmd : commandes) {
            final String adresse = cmd.getAdresse();
            final String codePostale = cmd.getCodePostale();

            if (adresse == null || codePostale == null) {
                System.out.println("Commande ignorée ID " + cmd.getIdCmd() + " à cause d'une adresse ou code postal null.");
                continue;
            }

            String[] parts = adresse.split(",");
            final String ville = (parts.length > 1 ?
                    parts[parts.length - 1].trim().replaceAll("\\d", "") :
                    adresse.trim()).trim();

            final String villeFinale = ville.isEmpty() ? "Inconnue" : ville;
            final String cle = villeFinale + "-" + codePostale;

            cityMap.computeIfAbsent(cle, k -> {
                Map<String, Object> donneesVille = new HashMap<>();
                donneesVille.put("nom", villeFinale);
                donneesVille.put("prenom", "");
                donneesVille.put("commandes", new ArrayList<Map<String, Object>>());
                return donneesVille;
            });

            List<Map<String, Object>> listeCommandes = (List<Map<String, Object>>) cityMap.get(cle).get("commandes");

            Map<String, Object> commandeInfo = new HashMap<>();
            commandeInfo.put("adresse", adresse);
            commandeInfo.put("codePostale", codePostale);

            if (!listeCommandes.stream().anyMatch(o -> o.get("adresse").equals(adresse))) {
                listeCommandes.add(commandeInfo);
            }
        }

        List<Map<String, Object>> clients = cityMap.values().stream()
                .map(dataVille -> {
                    Map<String, Object> client = new HashMap<>();
                    client.put("nom", dataVille.get("nom"));
                    client.put("prenom", dataVille.get("prenom"));
                    client.put("commandes", dataVille.get("commandes"));
                    return client;
                })
                .collect(Collectors.toList());

        System.out.println("Taille de la carte des villes : " + cityMap.size());
        cityMap.forEach((cle, valeur) ->
                System.out.println("Clé : " + cle + ", Commandes : " + valeur.get("commandes")));

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("locations", clients.isEmpty() ? new ArrayList<>() : clients);
        System.out.println("Réponse des zones de livraison : " + reponse);
        return reponse;
    }


}
