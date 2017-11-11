package com.seminario.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seminario.model.Postulacion;

public interface PostulacionRepository extends MongoRepository<Postulacion, String> {
	
	Postulacion findById(String id);
	List<Postulacion> findByIdPartido(String idPartido);

}
