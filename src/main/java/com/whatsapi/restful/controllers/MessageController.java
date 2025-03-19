package com.whatsapi.restful.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.whatsapi.restful.service.MessageService;

@RestController
public class MessageController {
    
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    

    @PostMapping("messages/batch-send")
    public void batchSendMessage(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        messageService.batchSendMessage(body);
    }

    @PostMapping("messages")
    public void sendMessage(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
        // messageService.sendMessage(body);
    }
}