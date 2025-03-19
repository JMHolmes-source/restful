package com.whatsapi.restful.controllers;

import com.whatsapi.restful.service.UserConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userconversations")
public class UserConversationController {

    private final UserConversationService userConversationService;

    @Autowired
    public UserConversationController(UserConversationService userConversationService) {
        this.userConversationService = userConversationService;
    }

    @PostMapping
    public void createUserConversation(@RequestParam Integer user_id,@RequestParam Integer conversation_id) {
        userConversationService.createUserConversation(user_id,conversation_id);
    }
}
