package com.whatsapi.restful.models;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User {
    String username;
    String useremail;
    public User(String username, String useremail) {
        this.username = username;
        this.useremail = useremail; 
    }
}
