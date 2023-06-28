package br.edu.ifsuldeminas.mch.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.estoque.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}