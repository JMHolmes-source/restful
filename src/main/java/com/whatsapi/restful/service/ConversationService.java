package com.whatsapi.restful.service;

import com.whatsapi.restful.repository.ConversationRepository;
import com.whatsapi.restful.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import org.json.JSONObject;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
   

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
        
    }

    public void createConversation(String body) {
        LocalDateTime createdAt = LocalDateTime.now();
        JSONObject jsonObject = new JSONObject(body);

        conversationRepository.createConversation(jsonObject.getString("name"), createdAt);
    }
}
