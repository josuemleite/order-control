package br.edu.ifsuldeminas.mch.estoque.resources;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsuldeminas.mch.estoque.entities.Order;
import br.edu.ifsuldeminas.mch.estoque.entities.OrderItem;
import br.edu.ifsuldeminas.mch.estoque.entities.Product;
import br.edu.ifsuldeminas.mch.estoque.entities.enums.OrderStatus;
import br.edu.ifsuldeminas.mch.estoque.services.OrderItemService;
import br.edu.ifsuldeminas.mch.estoque.services.OrderService;
import br.edu.ifsuldeminas.mch.estoque.services.ProductService;
import br.edu.ifsuldeminas.mch.estoque.services.UserService;

@Controller
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping(value = { "/", "/orders" })
	public String findAll(Model model) {
		List<Order> list = service.findAll();
		model.addAttribute("orders", list);
		return "orders";
	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Order> findById(@PathVariable Long id) {
//		Order obj = service.findById(id);
//		return ResponseEntity.ok().body(obj);
//	}
	
	@GetMapping("/orders/form")
	public String orderForm(Model model) {
	    model.addAttribute("order", new Order());
	    model.addAttribute("users", userService.findAll());
	    model.addAttribute("products", productService.findAll());
	    return "order_form";
	}
	
	@PostMapping("/orders/new")
	public String orderNew(@ModelAttribute("order") Order order, @RequestParam("selectedProducts") Long[] productIds, @RequestParam("quantities") Integer[] quantities) {
	    order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
	    order.setMoment(Instant.now());
	    Order savedOrder = service.insert(order);

	    for (int i = 0; i < productIds.length; i++) {
	        Long productId = productIds[i];
	        Integer quantity = quantities[i];

	        Optional<Product> optionalProduct = productService.findById(productId);
	        if (optionalProduct.isPresent()) {
	            Product product = optionalProduct.get();
	            OrderItem orderItem = new OrderItem(savedOrder, product, quantity, product.getPrice());
	            orderItemService.insert(orderItem);
	        }
	    }

	    return "redirect:/orders";
	}
	
	@GetMapping("/orders/update/{id}")
	public String orderUpdate(@PathVariable("id") Long id, Model model) {
	    Optional<Order> optOrder = service.findById(id);
	    if (optOrder.isPresent()) {
	        Order order = optOrder.get();
	        model.addAttribute("order", order);
	        model.addAttribute("users", userService.findAll());
	        model.addAttribute("products", productService.findAll());
	        model.addAttribute("items", order.getItems());
	        return "order_form";
	    } else {
	        return "redirect:/error";
	    }
	}
	
	@GetMapping("/orders/delete/{id}")
	public String orderDelete(@PathVariable("id") Long id) {
		Optional<Order> optOrder = service.findById(id);
		if (optOrder.isPresent()) {
			Order order = optOrder.get();
			service.delete(order.getId());
			return "redirect:/orders";
		} else {
			return "redirect:/error";
		}
	}
}