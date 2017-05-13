package com.clist.repositories.mongo.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clist.domain.entities.User;
import com.clist.repositories.UserRepository;

public interface UserRepositoryInfra extends MongoRepository<User, String> , UserRepository{

}
