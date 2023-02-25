package com.kam.userManagement.models;


import com.kam.userManagement.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Persistent_logins", schema = "public")
@Data
public class Persistent_logins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "series")
    private long series;

    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;

    @Column(name = "last_used")
    @CreationTimestamp
    private LocalDateTime last_used;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
