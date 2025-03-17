package com.whatsapi.restful.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
public class User{
    String username;
    String useremail;
    public User(String username, String useremail) {
        this.username = username;
        this.useremail = useremail; 
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUsername() {
        return username;
    }
}
