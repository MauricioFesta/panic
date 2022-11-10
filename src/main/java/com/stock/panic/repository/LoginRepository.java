package com.stock.panic.repository;

import com.stock.panic.model.Contas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Contas, String> {

}
