package br.edu.ifsuldeminas.mch.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.mch.estoque.entities.OrderItem;
import br.edu.ifsuldeminas.mch.estoque.repositories.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;

	public List<OrderItem> findAll() {
		return repository.findAll();
	}

	public Optional<OrderItem> findById(Long id) {
		return repository.findById(id);
	}

	public OrderItem insert(OrderItem obj) {
		return repository.save(obj);
	}

//	public void delete(Long id) {
//		try {
//			repository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DatabaseException(e.getMessage());
//		}
//	}
//
//	public OrderItem update(Long id, OrderItem obj) {
//		try {
//			OrderItem entity = repository.getReferenceById(id);
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(OrderItem entity, OrderItem obj) {
//		entity.setName(obj.getName());
//		entity.setDescription(obj.getDescription());
//		entity.setPrice(obj.getPrice());
//		entity.setImgUrl(obj.getImgUrl());
//	}
}