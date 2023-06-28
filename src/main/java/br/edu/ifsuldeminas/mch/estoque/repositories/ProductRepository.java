package br.edu.ifsuldeminas.mch.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.estoque.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}