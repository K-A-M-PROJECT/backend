package com.kam.order.repository;


import com.kam.order.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByIsDeletedFalse();
    List<Order> findAllByIsDeletedTrue();

    @Modifying
    @Query("UPDATE Order o SET o.isDeleted = :value WHERE o.id = :id")
    void updateIsDeletedStatus(@Param("id") long id, @Param("value") boolean value);
}
