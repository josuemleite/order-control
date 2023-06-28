package br.edu.ifsuldeminas.mch.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.estoque.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}