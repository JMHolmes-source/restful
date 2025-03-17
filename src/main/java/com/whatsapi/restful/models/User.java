package com.whatsapi.restful.models;

import javax.persistence.Entity;

import lombok.Data;
import org.springframework.http.HttpEntity;

@Data
public class User{
    String username;
    String useremail;
    public User(String username, String useremail) {
        this.username = username;
        this.useremail = useremail; 
    }
}
