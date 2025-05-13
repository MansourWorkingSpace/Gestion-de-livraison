package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.repository.ClientRepository;
import com.example.GestionDeLivraison.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public Client updateClient(Integer id, Client clientDetails) {
        Client client = getClientById(id);
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setAge(clientDetails.getAge());
        client.setTlf(clientDetails.getTlf());
        client.setEmail(clientDetails.getEmail());
        client.setMotdepasse(clientDetails.getMotdepasse());
        client.setPhotodeprofil(clientDetails.getPhotodeprofil());
        client.setAdresse(clientDetails.getAdresse());
        client.setCodePostale(clientDetails.getCodePostale());
        client.setZip(clientDetails.getZip());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> searchClients(String query) {
        return clientRepository.findByNomContainingOrPrenomContainingOrEmailContainingOrAdresseContaining(
                query, query, query, query);
    }
}