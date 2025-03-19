package com.whatsapi.restful.models.DTOs;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConversationListDTO {
    private String conversationName;
    private String lastSenderUsername;
    private String lastMessage;
    private LocalDateTime datetime;
}
