package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whatsapi.restful.models.Chat;
import com.whatsapi.restful.models.Message;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path = "/conversation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConvoController {

    @GetMapping("/{id}")
    Chat conversation() {
        Chat temp = new Chat();
        temp.addMessage(new Message("yaosile", "I'm doing well, thanks! What are you up to today?", "2023-10-01", "10:02:00"));
        temp.addMessage(new Message("greggyweggy", "Just relaxing. Might go for a walk later. You?", "2023-10-01", "10:03:00"));
        temp.addMessage(new Message("yaosile", "Nice! I’ve got some work to finish, but I might take a break later.","2023-10-01", "10:04:00"));
        temp.addMessage(new Message("greggyweggy", "Sounds like a plan. Don’t overwork yourself!", "2023-10-01", "10:05:00"));
        temp.addMessage(new Message("yaosile", "Haha, I’ll try. Enjoy your walk!", "2023-10-01", "10:06:00"));
        temp.addMessage(new Message("greggyweggy", "Thanks! Talk later!", "2023-10-01", "10:07:00"));
        return temp;
    }
}