package com.whatsapi.restful.service;

import com.whatsapi.restful.repository.ConversationRepository;
import com.whatsapi.restful.repository.UserConversationRepository;
import com.whatsapi.restful.repository.UserRepository;
import com.whatsapi.restful.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ConversationServiceTest {

    @Mock
    private JwtUtil jwtUtil;


    @InjectMocks
    private ConversationService conversationService;

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private UserConversationRepository userConversationRepository;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Manually set the jwtUtil on your conversationService
        Field jwtUtilField = null;
        try {
            jwtUtilField = ConversationService.class.getDeclaredField("jwtUtil");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        jwtUtilField.setAccessible(true);
        try {
            jwtUtilField.set(conversationService, jwtUtil);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testCreateConversation() {
        // Arrange
        String authHeader = "Bearer validToken";
        String email = "test@example.com";
        String body = "{ \"name\": \"Test Conversation\" }";
        int userId = 1;
        int conversationId = 2;

        // Mock JwtUtil to return the email
        when(jwtUtil.extractEmail("validToken")).thenReturn(email);

        // Mock UserRepository to return the user ID
        when(userRepository.getUserId(email)).thenReturn(userId);

        // Mock ConversationRepository to return the conversation ID
        when(conversationRepository.createConversation("Test Conversation")).thenReturn(conversationId);

        // Act
        conversationService.createConversation(body, authHeader);

        // Assert
        // Verify JwtUtil.extractEmail is called with the correct token
        verify(jwtUtil, times(1)).extractEmail("validToken");

        // Verify UserRepository.getUserId is called with the correct email
        verify(userRepository, times(1)).getUserId(email);

        // Verify ConversationRepository.createConversation is called with the correct name
        verify(conversationRepository, times(1)).createConversation("Test Conversation");

        // Verify UserConversationRepository.createUserConversation is called with the correct user ID and conversation ID
        verify(userConversationRepository, times(1)).createUserConversation(userId, conversationId);
    }
}