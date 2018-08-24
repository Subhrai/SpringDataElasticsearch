package com.example.ses.repository;

import com.example.ses.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByRole(String role);
    List<User> findByUserName(String userName);
    List<User> findByPassWord (String passWord);
    List<User> findByUserNameAndPassWordAllIgnoreCase(String userName, String passWord);
}
