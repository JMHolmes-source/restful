package com.whatsapi.restful.controllers;

import com.whatsapi.restful.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add-user")
    public void adddUserToConversation(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        conversationService.addUserToConversation(body);
    }
}
