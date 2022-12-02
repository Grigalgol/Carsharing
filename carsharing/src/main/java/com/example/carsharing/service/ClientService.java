package com.example.carsharing.service;

import com.example.carsharing.dto.UserDto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Role;
import com.example.carsharing.models.User;
import com.example.carsharing.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(UserDto userDto) {
        Client client = new Client(
                userDto.getPhone(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPasswordSeries() + userDto.getPasswordNumber()
        );
        return clientRepository.save(client);
    }

}
