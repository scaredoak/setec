package com.setec.crud.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.setec.crud.domain.Client;
import com.setec.crud.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        String email = client.getEmail();
        String name = client.getName();

        Optional<Client> existingEmail = clientRepository.findByEmail(email);
        Optional<Client> existingName = clientRepository.findByName(name);

        if (existingEmail.isPresent()) {
            if (!existingEmail.get().getId().equals(client.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already exists with email: " + email);
            }
        }

        if (existingName.isPresent()) {
            if (!existingName.get().getId().equals(client.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already exists with name: " + name);
            }
        }

        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id));
    }

    public Client findByName(String name) {
        return clientRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with name: " + name));
    }

    public void update(Client client) {
        findById(client.getId());
        save(client);
    }

    public void delete(Long clientId) {
        findById(clientId);
        clientRepository.deleteById(clientId);
    }
}
