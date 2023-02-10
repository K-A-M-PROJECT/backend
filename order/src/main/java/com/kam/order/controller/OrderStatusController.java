package com.kam.order.controller;


import com.kam.order.models.order.OrderStatus;
import com.kam.order.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("order/status")
@AllArgsConstructor
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository orderStatusRepository;


    @PostMapping
    public ResponseEntity<OrderStatus> addStatus(@RequestBody OrderStatus orderStatus){
       OrderStatus status = this.orderStatusRepository.save(orderStatus);
       return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderStatus>> getAllStatus(){
        List<OrderStatus> status = this.orderStatusRepository.findAll();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable("id") int id){
        this.orderStatusRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> updateStatus(@RequestBody OrderStatus orderStatus){
        OrderStatus status = this.orderStatusRepository.save(orderStatus);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }



}
