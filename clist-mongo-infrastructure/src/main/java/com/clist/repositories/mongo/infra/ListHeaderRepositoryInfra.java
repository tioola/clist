package com.clist.repositories.mongo.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clist.domain.entities.ListHeader;
import com.clist.repositories.ListHeaderRepository;

public interface ListHeaderRepositoryInfra extends MongoRepository<ListHeader, String>, ListHeaderRepository{

}
