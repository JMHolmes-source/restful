package com.whatsapi.restful.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    String email;
    String username;
    String first_name;
    String last_name;

    public User() {}
}
