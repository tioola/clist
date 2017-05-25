package com.clist.repositories.mongo.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clist.domain.entities.Row;
import com.clist.repositories.RowRepository;

public interface RowRepositoryInfra extends MongoRepository<Row, String>, RowRepository{

}
