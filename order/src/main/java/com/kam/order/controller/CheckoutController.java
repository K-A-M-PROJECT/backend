package com.kam.order.controller;

import com.kam.order.dto.Purchase;
import com.kam.order.dto.PurchaseResponse;
import com.kam.order.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/checkout")
@AllArgsConstructor
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;


    @PostMapping
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

}









