package com.example.vh.repository;

import com.example.vh.models.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepositry extends MongoRepository<Owner, String> {
}
