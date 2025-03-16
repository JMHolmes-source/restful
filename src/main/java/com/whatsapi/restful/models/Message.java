package com.whatsapi.restful.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Message {
    private int userID;
    private int recipient;
    private String message;

    public Message(int uid, int rec) {
        this.userID = uid;
        this.recipient = rec;
    }
}
