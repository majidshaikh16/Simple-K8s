package com.main.SimpleWeb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.SimpleWeb.entity.OrderDetails;


@Repository
public interface OrderRepository extends CrudRepository<OrderDetails, Integer> {

}
