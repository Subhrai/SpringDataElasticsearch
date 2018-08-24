package com.example.ses.service;

import com.example.ses.model.User;

import java.util.*;

public interface UserService {

    User save(User User);

    void delete(User User);

    Iterable<User> findAll();

    List<User> findByRole(String role);


}
