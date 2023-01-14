package com.SOCprojet.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SOCprojet.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
