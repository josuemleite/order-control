package br.edu.ifsuldeminas.mch.estoque.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.estoque.entities.Product;
import br.edu.ifsuldeminas.mch.estoque.services.ProductService;
import jakarta.validation.Valid;

@Controller
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/products")
	public String findAll(Model model) {
		List<Product> list = service.findAll();
		model.addAttribute("products", list);
		return "products";
	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Product> findById(@PathVariable Long id) {
//		Product obj = service.findById(id);
//		return ResponseEntity.ok().body(obj);
//	}
	
	@GetMapping(value = "/products/form")
	public String productForm(@ModelAttribute("product") Product product) {
		return "product_form";
	}
	
	@PostMapping(value = "/products/new")
	public String productNew(@Valid
							 @ModelAttribute("product")
							 Product product,
							 BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "product_form";
		}
		
		service.insert(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products/update/{id}")
	public String productUpdate(@PathVariable("id") Long id, Model model) {
		Optional<Product> optProduct = service.findById(id);
		if (optProduct.isPresent()) {
			Product product = optProduct.get();
			model.addAttribute("product", product);
			return "product_form";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/products/delete/{id}")
	public String productDelete(@PathVariable("id") Long id) {
		Optional<Product> optProduct = service.findById(id);
		if (optProduct.isPresent()) {
			Product product = optProduct.get();
			service.delete(product.getId());
			return "redirect:/products";
		} else {
			return "error";
		}
	}
}