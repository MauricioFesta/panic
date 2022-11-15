package com.stock.panic.repository_impl;;


import com.stock.panic.repository.ContaRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.stock.panic.model.Conta;

@Repository
public class ContaRepository implements ContaRepositoryInterface {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Conta> getAll() {
		return mongoTemplate.findAll(Conta.class);
	}

	@Override
	public Conta getLogin(String email, String password){

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		query.addCriteria(Criteria.where("senha").is(password));

		return mongoTemplate.findOne(query, Conta.class);
	}

	
}
