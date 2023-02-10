package com.kam.order.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode
@DiscriminatorValue("admin")
public class Admin extends User implements Serializable {

    @Column(name = "specialization")
    private String specialization;

}
