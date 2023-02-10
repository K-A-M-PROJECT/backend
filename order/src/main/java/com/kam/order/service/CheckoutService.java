package com.kam.order.service;

import com.kam.order.repository.CustomerRepository;
import com.kam.order.dto.Purchase;
import com.kam.order.dto.PurchaseResponse;
import com.kam.order.models.user.Customer;
import com.kam.order.models.order.Order;
import com.kam.order.models.order.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@AllArgsConstructor
public class CheckoutService {

    @Autowired
    private CustomerService customerService;

    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrder().getOrderItems();
        orderItems.stream().forEach(item -> item.setOrder(order));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.getOrders().add(order);
        order.setCustomer(customer);

        // save to the database
        customerService.save(customer);

        // return a response
        return new PurchaseResponse(order.getOrderTrackingNumber());
    }


}









