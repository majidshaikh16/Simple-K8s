package com.main.SimpleWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.SimpleWeb.entity.OrderDetails;
import com.main.SimpleWeb.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(path="/add/order/details")
	public boolean addOrder(@RequestBody OrderDetails details) {
		return orderService.addOrder(details);
	}
}
