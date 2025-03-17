package com.whatsapi.restful.models;

import lombok.Data;
import org.springframework.http.HttpEntity;

import java.util.*;

import javax.persistence.Entity;


@Data
public class Chat<T> extends HttpEntity<T> {
    private List<Message> messages;

    public Chat(List<Message> messages) {
        this.messages = new ArrayList<Message>(messages);
    }

    public Chat() {
        this.messages = new ArrayList<Message>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
