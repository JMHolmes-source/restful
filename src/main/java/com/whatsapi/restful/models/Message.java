package com.whatsapi.restful.models;

import lombok.Data;

@Data
public class Message{
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
