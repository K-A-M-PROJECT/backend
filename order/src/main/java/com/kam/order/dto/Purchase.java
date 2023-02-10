package com.kam.order.dto;

import com.kam.order.models.order.Address;
import com.kam.order.models.user.Customer;
import com.kam.order.models.order.Order;
import lombok.Data;


@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;

}
