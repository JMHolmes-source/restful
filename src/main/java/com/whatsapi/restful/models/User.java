package com.whatsapi.restful.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User{
    @Id
    String username;

    @Basic
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
