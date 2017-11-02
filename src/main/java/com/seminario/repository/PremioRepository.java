package com.seminario.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seminario.model.Premio;

public interface PremioRepository extends MongoRepository<Premio, String> {

	List<Premio> findByIdUsuario(String idUsuario);
	
}
