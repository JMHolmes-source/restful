package com.whatsapi.restful.controllers;

import com.whatsapi.restful.models.Conversation;
import com.whatsapi.restful.models.ConversationListDTO;
import com.whatsapi.restful.models.Message;
import com.whatsapi.restful.service.ConversationService;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/create")
    public void createConversation(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        conversationService.createConversation(body, authHeader);
    }

    @GetMapping("/list")
    public List<ConversationListDTO> listConversations(@RequestHeader("Authorization") String authHeader) {
        return conversationService.listConversation(authHeader);
    }

    @PostMapping("/show")
    public List<Message> readConversation(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        return conversationService.showConversation(body, authHeader);
    }

    @PostMapping("/message")
    public void sendMessage(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        conversationService.sendMessage(body, authHeader);
    }
}
