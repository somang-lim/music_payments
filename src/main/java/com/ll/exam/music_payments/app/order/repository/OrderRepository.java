package com.ll.exam.music_payments.app.order.repository;

import com.ll.exam.music_payments.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
