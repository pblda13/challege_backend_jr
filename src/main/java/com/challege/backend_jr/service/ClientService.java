package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.exception.ClientNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public Client createClient(Client client) {
        Client savedClient = clientRepository.save(client);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("Client registered successfully"));
        return savedClient;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){
       return clientRepository.findById(id);
    }


    @Transactional
    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));

        existingClient.setName(client.getName());
        existingClient.setRegistration(client.getRegistration());
        existingClient.setContractActive(client.isContractActive());

        return clientRepository.save(existingClient);
    }

    @Transactional
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
        clientRepository.delete(client);
    }
}
