package com.vti.repository;

import com.vti.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IOrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
}
