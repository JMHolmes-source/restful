package com.whatsapi.restful.service;


import com.whatsapi.restful.repository.UserConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserConversationService {
    private final UserConversationRepository userConversationRepository;

    @Autowired
    public UserConversationService(UserConversationService userConversationRepository) {
        this.userConversationRepository = userConversationRepository;
    }

    public void createUserConversation(Integer user_id, Integer conversation_id) {
        LocalDateTime createdAt = LocalDateTime.now();
        userConversationRepository.createUserConversation(user_id, conversation_id);
    }
}
