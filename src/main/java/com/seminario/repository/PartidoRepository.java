package com.seminario.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seminario.model.Partido;

public interface PartidoRepository extends MongoRepository<Partido, String> {
	
	Partido findById(String id);
	List<Partido> findByIdUsuarioJugador(String idUsuarioJugador);
	List<Partido> findByIdUsuarioOrganizador(String idUsuarioOrganizador);

}
