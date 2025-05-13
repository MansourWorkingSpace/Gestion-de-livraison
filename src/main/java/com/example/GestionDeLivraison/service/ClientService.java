package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Client;
import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client createClient(Client client);
    Client getClientById(Integer id);
    Client updateClient(Integer id, Client client);
    void deleteClient(Integer id);
    List<Client> searchClients(String query);
}