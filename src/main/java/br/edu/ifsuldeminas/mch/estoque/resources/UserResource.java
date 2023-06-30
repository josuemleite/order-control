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

import br.edu.ifsuldeminas.mch.estoque.entities.User;
import br.edu.ifsuldeminas.mch.estoque.services.UserService;
import jakarta.validation.Valid;

@Controller
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/users")
	public String findAll(Model model) {
		List<User> list = service.findAll();
		model.addAttribute("users", list);
		return "users";
	}
	
	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("user") User user) {
		return "user_form";
	}
	
	@PostMapping("/users/new")
	public String userNew(@Valid
						  @ModelAttribute("user")
						  User user,
						  BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "user_form";
		}
		
		service.insert(user);
		return "redirect:/users";
	}
	
	@GetMapping("/users/update/{id}")
	public String userUpdate(@PathVariable("id") Long id, Model model) {
		Optional<User> optUser = service.findById(id);
		if (optUser.isPresent()) {
			User user = optUser.get();
			model.addAttribute("user", user);
			return "user_form";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable("id") Long id) {
		Optional<User> optUser = service.findById(id);
		if (optUser.isPresent()) {
			User user = optUser.get();
			service.delete(user.getId());
			return "redirect:/users";
		} else {
			return "error";
		}
	}
}