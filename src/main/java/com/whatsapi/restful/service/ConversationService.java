package com.whatsapi.restful.service;

import com.whatsapi.restful.models.Message;
import com.whatsapi.restful.repository.ConversationRepository;
import com.whatsapi.restful.repository.MessageRepository;
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
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    // private final UserConversationRepository userConversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository,
            // UserConversationRepository userConversationRepository,
            UserRepository userRepository,
            MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        // this.userConversationRepository = userConversationRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void createConversation(String body, String authHeader) {
        JSONObject jsonObject = new JSONObject(body);
        conversationRepository.createConversation(jsonObject.getString("name"));
    }

    public List<Message> showConversation(String body, String authHeader) {
        JSONObject json = new JSONObject(body);
        int conversation_id = conversationRepository.getConversationID(json.getString("conversation"));
        return messageRepository.getMessages(conversation_id);
    }

    public void sendMessage(String body, String authHeader) {
        JSONObject json = new JSONObject(body);

        String email = jwtUtil.extractEmail(authHeader);
        int user_id = userRepository.getUserId(email);
        int convo_id = conversationRepository.getConversationID(json.getString("conversation"));

        messageRepository.sendMessage(json.getString("text"), convo_id, user_id);
    }



    // public void addUserToConversation(String body) {
    // JSONObject json = new JSONObject(body);
    // int userID = userRepository.getUserIdByUsername(json.getString("username"));
    // int conversation_id =
    // conversationRepository.getConversationID(json.getString("conversationName"));
    // userConversationRepository.createUserConversation(userID,conversation_id);
    // }
}
