package com.whatsapi.restful.service;

import com.whatsapi.restful.models.*;
import com.whatsapi.restful.models.DTOs.*;
import com.whatsapi.restful.repository.*;
import com.whatsapi.restful.util.JwtUtil;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConversationServiceTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ConversationService conversationService;

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
        String body = "{ \"name\": \"Test Conversation\" }";

        // Act
        conversationService.createConversation(body, authHeader);

        // Assert
        verify(conversationRepository, times(1)).createConversation("Test Conversation");
    }

    @Test
    void testListConversation() {
        // Arrange
        String authHeader = "Bearer validToken";
        List<Conversation> conversations = new ArrayList<>();
        Conversation conversation = new Conversation();
        conversation.setConversation_id(1);
        conversation.setConversation_name("Test Conversation");
        conversations.add(conversation);

        Message message = new Message();
        message.setMessage_text("Hello");
        message.setSender_id(1);
        message.setSent_at(LocalDateTime.now());

        when(conversationRepository.getConversations()).thenReturn(conversations);
        when(messageRepository.getLastMessage(1)).thenReturn(message);
        when(userRepository.findUsernameFromId(1)).thenReturn("testuser");

        // Act
        List<ConversationListDTO> result = conversationService.listConversation(authHeader);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Conversation", result.get(0).getConversationName());
        assertEquals("Hello", result.get(0).getLastMessage());
        assertEquals("testuser", result.get(0).getLastSenderUsername());
        verify(conversationRepository, times(1)).getConversations();
        verify(messageRepository, times(1)).getLastMessage(1);
        verify(userRepository, times(1)).findUsernameFromId(1);
    }

    @Test
    void testShowConversation() {
        // Arrange
        String authHeader = "Bearer validToken";
        String body = "{ \"conversation\": \"Test Conversation\" }";

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setMessage_text("Hello");
        message.setSender_id(1);
        message.setSent_at(LocalDateTime.now());
        messages.add(message);

        when(conversationRepository.getConversationID("Test Conversation")).thenReturn(1);
        when(messageRepository.getMessages(1)).thenReturn(messages);
        when(userRepository.findUsernameFromId(1)).thenReturn("testuser");

        // Act
        List<MessageDTO> result = conversationService.showConversation(body, authHeader);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getMessage());
        assertEquals("testuser", result.get(0).getUsername());
        verify(conversationRepository, times(1)).getConversationID("Test Conversation");
        verify(messageRepository, times(1)).getMessages(1);
        verify(userRepository, times(1)).findUsernameFromId(1);
    }

    @Test
    void testSendMessage() {
        // Arrange
        String authHeader = "Bearer validToken";
        String body = "{ \"conversation\": \"Test Conversation\", \"text\": \"Hello\" }";

        when(jwtUtil.extractEmail("Bearer validToken")).thenReturn("test@example.com");

        when(userRepository.getUserId("test@example.com")).thenReturn(1);

        when(conversationRepository.getConversationID("Test Conversation")).thenReturn(1);

        conversationService.sendMessage(body, authHeader);

        verify(jwtUtil, times(1)).extractEmail("Bearer validToken");

        verify(userRepository, times(1)).getUserId("test@example.com");

        verify(conversationRepository, times(1)).getConversationID("Test Conversation");

        verify(messageRepository, times(1)).sendMessage("Hello", 1, 1);
    }
}