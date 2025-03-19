package com.whatsapi.restful.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "conversations", schema = "public")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coversation_id;
    private String conversation_name;
    private LocalDateTime created_at;
}
