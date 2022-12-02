package com.example.carsharing.service;

import com.example.carsharing.dto.UserDto;
import com.example.carsharing.models.User;
import com.example.carsharing.models.Role;
import com.example.carsharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User save(UserDto userDto) {
        User user = new User(
                userDto.getPhone(),
                userDto.getFirstName(),
                userDto.getLastName(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getPasswordSeries() + userDto.getPasswordNumber(),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }
}
