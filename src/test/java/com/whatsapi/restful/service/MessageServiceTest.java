package com.whatsapi.restful.service;

import com.whatsapi.restful.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSendMessage() {
        // Arrange
        String body = "{ \"user\": \"1\", \"receiver\": \"2\", \"message\": \"Hi\", \"read\": true, \"sentat\": \"2023-10-01T12:00:00\" }";
        Integer senderId = 1;
        Integer receiverId = 2;
        String messageText = "Hi";
        boolean read = true;
        LocalDateTime sentAt = LocalDateTime.parse("2023-10-01T12:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Act
        messageService.sendMessage(body);

        // Assert
        verify(messageRepository, times(1)).sendMessage(senderId, receiverId, messageText, sentAt, read);
    }
}