package com.example.ses.service;

import com.example.ses.model.User;
import com.example.ses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    /**
     * implement UserService interface which contains method to serve user request
     */

    /**
     * UserRepository contains implementation of {@link org.springframework.data.elasticsearch.repository.ElasticsearchRepository}
     * this will provide connection with elasticsearch
     */
    @Autowired
    public UserRepository userRepository;

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public void delete(User User) {
        userRepository.delete(User);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }


}
