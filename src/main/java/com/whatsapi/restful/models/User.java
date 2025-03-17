package com.whatsapi.restful.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import org.springframework.http.HttpEntity;

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
}
