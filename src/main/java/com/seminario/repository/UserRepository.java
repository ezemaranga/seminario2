package com.seminario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seminario.model.Usuario;

public interface UserRepository extends MongoRepository<Usuario, String> {

	Usuario findById(String id);
	Usuario findByUsername(String username);
	Usuario findByEmail(String email);
	
}
