package com.stock.panic.repository_impl;;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.stock.panic.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.stock.panic.model.Usuario;
import com.stock.panic.repository.UsuarioRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.stock.panic.model.Usuario;

@Repository
public class UsuarioRepository implements UsuarioRepositoryInterface {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Usuario> getAll() {
            return mongoTemplate.findAll(Usuario.class);
	}

	@Override
	public Usuario getLogin(String email){

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            //query.addCriteria(Criteria.where("senha").is(password));

            return mongoTemplate.findOne(query, Usuario.class);
	}

	
}
