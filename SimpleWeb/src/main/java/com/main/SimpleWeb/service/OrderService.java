package com.main.SimpleWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.SimpleWeb.entity.OrderDetails;
import com.main.SimpleWeb.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	public boolean addOrder(OrderDetails entity) {
		try {
			orderRepository.save(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
}
