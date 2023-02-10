package com.kam.order.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kam.order.models.order.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer")
@Data
@EqualsAndHashCode
@DiscriminatorValue("customer")
public class Customer extends User implements Serializable {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();
}









