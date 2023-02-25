package com.kam.userManagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kam.userManagement.models.user.User;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name="address", schema = "public")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="zip_code")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}