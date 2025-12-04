package com.example.vh.repository;

import com.example.vh.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
