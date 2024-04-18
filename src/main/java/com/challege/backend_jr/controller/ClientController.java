package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updateClient = clientService.updateClient(id, client);
        return ResponseEntity.ok().body(updateClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> ListClient() {
        List<Client> clientList = clientService.getAllClient();
        return ResponseEntity.ok().body(clientList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        clientService.clientDelete(id);
        return ResponseEntity.noContent().build();
    }
}
