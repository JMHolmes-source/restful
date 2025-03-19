package com.whatsapi.restful.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Data;

@Entity
@Data
@Table(name = "messages", schema = "public")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;
    private int sender_id;
    private String message_text;
    private LocalDateTime sent_at;
    private boolean read_bool;
    private int conversation_id;
}
