package com.setec.crud.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.setec.crud.domain.Costumer;
import com.setec.crud.repository.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostumerService {

    private final CostumerRepository costumerRepository;

    public List<Costumer> findAll() {
        return costumerRepository.findAll();
    }

    public Costumer save(Costumer costumer) {
        String email = costumer.getEmail();

        Optional<Costumer> existingEmail = costumerRepository.findByEmail(email);

        if (existingEmail.isPresent()) {
            if (!existingEmail.get().getId().equals(costumer.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Costumer already exists with email: " + email);
            }
        }

        return costumerRepository.save(costumer);
    }

    public Costumer findById(Long id) {
        return costumerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Costumer not found with id: " + id));
    }

    public Costumer findByName(String name) {
        return costumerRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Costumer not found with name: " + name));
    }

    public void update(Costumer costumer) {
        findById(costumer.getId());
        save(costumer);
    }

    public void delete(Long id) {
        findById(id);
        costumerRepository.deleteById(id);
    }
}
