package com.kam.order.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Entity
@Data
@Table(name = "OrderStatus")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "status" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> order = new ArrayList<>();


}
