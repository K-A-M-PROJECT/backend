package com.kam.order.service;


import com.kam.order.models.order.Order;
import com.kam.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;


    // get All orders
    public List<Order> getAllOrders(){
        return this.orderRepository.findAllByIsDeletedFalse();
    }

    // get All Deleted Order
    public List<Order> getAllDeletedOrders(){
        return this.orderRepository.findAllByIsDeletedTrue();
    }

    // Save and Update Order
    public Order saveOrder(Order order){
        return this.orderRepository.save(order);
    }

    // Delete order by Soft Delete
    // Restore the deleted Order
    public void updateIsDeletedStatus(long id, boolean IsDeleted){
        this.orderRepository.updateIsDeletedStatus(id, IsDeleted);
    }

    // get orders of the customer
    public Set<Order> getCustomerOrdersById(long customerId){
        return this.customerService.getCustomerById(customerId).getOrders();
    }
}
