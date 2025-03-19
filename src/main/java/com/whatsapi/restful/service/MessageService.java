package com.whatsapi.restful.service;

import com.whatsapi.restful.repository.MessageRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.json.JSONObject;


@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void batchSendMessage(String body) {
        JSONObject json = new JSONObject(body);
        Integer senderId = Integer.parseInt(json.getString("user"));

        List<Integer> receiverEmails = json.getJSONArray("receivers").toList().stream()
            .map(o -> Integer.parseInt(o.toString()))  // Incorrect conversion to Integer
            .collect(Collectors.toList());


        String messageText = json.getString("message");
        boolean read = json.optBoolean("read", false);  // Default to false if not provided
        LocalDateTime sentAt = LocalDateTime.now();  // Get current timestamp

        for (Integer receiverId : receiverEmails) {
            messageRepository.batchSendMessage(senderId, receiverId, messageText, sentAt, read);
        }
    }

    public void sendMessage(String body) {
        JSONObject json = new JSONObject(body);
        Integer senderId = Integer.parseInt(json.getString("user"));
        
        Integer receiverId = Integer.parseInt(json.getString("receiver"));  // Assuming 'receiver' is a single email or identifier
        
        String messageText = json.getString("message");
        boolean read = json.optBoolean("read", false);  // Default to false if not provided
        LocalDateTime sentAt = LocalDateTime.parse(json.getString("sentat"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        messageRepository.sendMessage(senderId, receiverId, messageText, sentAt, read);
    }
}
