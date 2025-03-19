package com.whatsapi.restful.service;

import com.whatsapi.restful.repository.ConversationRepository;
import com.whatsapi.restful.repository.UserConversationRepository;
import com.whatsapi.restful.repository.UserRepository;
import com.whatsapi.restful.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import org.json.JSONObject;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private JwtUtil jwtUtil;

    private final ConversationRepository conversationRepository;
    private final UserConversationRepository userConversationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository,
            UserConversationRepository userConversationRepository,
            UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userConversationRepository = userConversationRepository;
        this.userRepository = userRepository;
    }

    public void createConversation(String body, String authHeader) {
        String email = jwtUtil.extractEmail(authHeader.replace("Bearer ", ""));
        JSONObject jsonObject = new JSONObject(body);
        userConversationRepository.createUserConversation(userRepository.getUserId(email),
                conversationRepository.createConversation(jsonObject.getString("name")));
    }
}
