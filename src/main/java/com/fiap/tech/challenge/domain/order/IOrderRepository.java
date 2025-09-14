package com.fiap.tech.challenge.domain.order;

import com.fiap.tech.challenge.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IOrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
