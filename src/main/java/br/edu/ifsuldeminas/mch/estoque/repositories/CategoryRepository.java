package br.edu.ifsuldeminas.mch.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.estoque.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}