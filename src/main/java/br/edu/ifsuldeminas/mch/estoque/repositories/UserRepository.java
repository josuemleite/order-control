package br.edu.ifsuldeminas.mch.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.estoque.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}