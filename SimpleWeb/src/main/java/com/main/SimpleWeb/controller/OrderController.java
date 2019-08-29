package com.main.SimpleWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.SimpleWeb.entity.OrderDetails;
import com.main.SimpleWeb.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/orders")
	public List<OrderDetails> getOrderDetails(){
		return orderService.getOrders();
	}
	
	@PostMapping(path="/add/order/details")
	public boolean addOrder(@RequestBody OrderDetails details) {
		return orderService.addOrder(details);
	}
}
