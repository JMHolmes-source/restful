package com.whatsapi.restful.models;

import javax.persistence.Entity;

import lombok.Data;
import org.springframework.http.HttpEntity;

public class Message<T> extends HttpEntity<T> {
    private String username;
    private String message;
    private String date;
    private String time;

    public Message (String username, String message, String date, String time) {
        this.username = username;
        this.message = message;
        this.date = date;
        this.time = time;
    }
}
