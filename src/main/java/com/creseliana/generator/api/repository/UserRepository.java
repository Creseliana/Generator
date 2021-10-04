package com.creseliana.generator.api.repository;

import com.creseliana.generator.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByLuckyNumber(int luckyNumber);
}
