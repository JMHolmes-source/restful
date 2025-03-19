package com.whatsapi.restful.models.DTOs;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDTO {
    private String username;
    private String message;
    private LocalDateTime datetime;
}

