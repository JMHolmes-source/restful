package com.whatsapi.restful.models;

import lombok.Data;

@Data
public class User {
    String username;
    String useremail;
    public User(String username, String useremail) {
        this.username = username;
        this.useremail = useremail;
    }
}
