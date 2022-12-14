package com.example.carsharing.service;

import com.example.carsharing.dto.UserDto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.models.User;
import com.example.carsharing.repository.ClientRepository;
import com.example.carsharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
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

    public Client findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public Client updateClient(UserDto userDto) {
        Client client = clientRepository.findByPhone(userDto.getPhone());
        User user = userRepository.findByPhone(userDto.getPhone());
        client.setFirstName(userDto.getFirstName());
        user.setFirstName(userDto.getFirstName());
        client.setLastName(userDto.getLastName());
        user.setLastName(userDto.getLastName());
        client.setPassportData(userDto.getPasswordSeries()+userDto.getPasswordNumber());
        user.setPassportData(userDto.getPasswordSeries()+userDto.getPasswordNumber());
        userRepository.save(user);
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.clientRepository.findAll(pageable);
    }

}
