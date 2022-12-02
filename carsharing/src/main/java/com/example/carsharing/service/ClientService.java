package com.example.carsharing.service;

import com.example.carsharing.dto.ClientDto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Role;
import com.example.carsharing.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClientService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    public Client save(ClientDto clientDto) {
        Client client = new Client(
                clientDto.getPhone(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                passwordEncoder.encode(clientDto.getPassword()),
                clientDto.getPasswordSeries() + clientDto.getPasswordNumber(),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return clientRepository.save(client);
    }
}
