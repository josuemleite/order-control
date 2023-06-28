package br.edu.ifsuldeminas.mch.estoque.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.estoque.entities.Category;
import br.edu.ifsuldeminas.mch.estoque.services.CategoryService;

@Controller
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping(value = "/categories")
	public String findAll(Model model) {
		List<Category> list = service.findAll();
		model.addAttribute("categories", list);
		return "categories";
	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Category> findById(@PathVariable Long id) {
//		Category obj = service.findById(id);
//		return ResponseEntity.ok().body(obj);
//	}
	
	@GetMapping(value = "/categories/form")
	public String categoryForm(@ModelAttribute("category") Category category) {
		return "category_form";
	}
	
	@PostMapping(value = "/categories/new")
	public String categoryNew(@ModelAttribute("category") Category category) {
		service.insert(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/update/{id}")
	public String categoryUpdate(@PathVariable("id") Long id, Model model) {
		Optional<Category> optCategory = service.findById(id);
		if (optCategory.isPresent()) {
			Category category = optCategory.get();
			model.addAttribute("category", category);
			return "category_form";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/categories/delete/{id}")
	public String categoryDelete(@PathVariable("id") Long id) {
		Optional<Category> optCategory = service.findById(id);
		if (optCategory.isPresent()) {
			Category category = optCategory.get();
			service.delete(category.getId());
			return "redirect:/categories";
		} else {
			return "error";
		}
	}
}