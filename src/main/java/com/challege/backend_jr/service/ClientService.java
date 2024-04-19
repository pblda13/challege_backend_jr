package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.exception.ClientNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private KafkaProducer kafkaProducer;


    public Client createClient(Client client) {
        kafkaProducer.sendMessage("Client registered successfully");
        return clientRepository.save(client);
    }

    public List<Client> getAllClient() {
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client updateClient(Long id, Client client) {
        Client client1 = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente not found with id: " + id));

        client1.setName(client.getName());
        client1.setUsername(client.getUsername());
        client1.setPassword(client.getPassword());
        client1.setContractActive(client.isContractActive());
        client1.setRole(client.getRole());

        return clientRepository.save(client1);
    }

    public void clientDelete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente not found with id: " + id));
        clientRepository.delete(client);
    }
}
