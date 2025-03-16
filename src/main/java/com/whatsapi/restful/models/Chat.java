package com.whatsapi.restful.models;

import lombok.Data;
import java.util.*;;

@Data
public class Chat {
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
