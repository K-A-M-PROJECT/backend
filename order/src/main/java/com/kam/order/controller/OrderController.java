package com.kam.order.controller;


import com.kam.order.models.order.Order;
import com.kam.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order SavedOrder = this.orderService.saveOrder(order);
        return new ResponseEntity<>(SavedOrder, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders = this.orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        Order updatedOrder = this.orderService.saveOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    @Operation(summary = "To Delete the order, set IsDeleted Parameter by True value and vice versa")
    @DeleteMapping(path = "/{id}/{IsDeleted}")
    public ResponseEntity<?> updateIsDeletedStatus(@PathVariable("id") long id,
                                                   @PathVariable("IsDeleted")boolean IsDeleted){
        this.orderService.updateIsDeletedStatus(id, IsDeleted);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(path = "/getDeletedOrders")
    public ResponseEntity<List<Order>> getAllDeletedOrders(){
        List<Order> orders = this.orderService.getAllDeletedOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(path = "/customerOrders/{id}")
    public ResponseEntity<Set<Order>> getCustomerOrders(@PathVariable("id") long id){
        Set<Order> orders = this.orderService.getCustomerOrdersById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }





}
