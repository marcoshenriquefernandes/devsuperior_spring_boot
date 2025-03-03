package com.devsuperior.clientscrud.services;

import com.devsuperior.clientscrud.dto.ClientDTO;
import com.devsuperior.clientscrud.entities.Client;
import com.devsuperior.clientscrud.repositories.ClientRepository;
import com.devsuperior.clientscrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientDTO::new);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToEntity(dto, client);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = repository.getReferenceById(id);
            copyDtoToEntity(dto, client);
            return new ClientDTO(repository.save(client));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Cliente inexistente");

        repository.deleteById(id);
    }

    public void copyDtoToEntity(ClientDTO dto, Client client){
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }

}
