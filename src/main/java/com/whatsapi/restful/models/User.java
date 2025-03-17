package com.whatsapi.restful.models;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    String email;
    String username;
    String first_name;
    String last_name;
}
