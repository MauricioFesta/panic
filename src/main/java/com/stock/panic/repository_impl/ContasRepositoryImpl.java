package com.stock.panic.repository_impl;;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.stock.panic.repository.ContasRepository;
import com.stock.panic.model.Contas;

@Repository
public class ContasRepositoryImpl implements ContasRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Contas> getAll() {
		return mongoTemplate.findAll(Contas.class);
	}

	@Override
	public Contas getLogin(String email, String password){

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		return mongoTemplate.findOne(query, Contas.class);
	}

	
}
