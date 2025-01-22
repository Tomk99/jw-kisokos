package com.tomk99.jwkisokos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private boolean isAllowed;
    private boolean isAdmin;
}
