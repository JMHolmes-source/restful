package com.whatsapi.restful.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserQuery() {
        // Arrange
        String email = "test@example.com";
        String username = "testuser";
        doNothing().when(userRepository).createUserQuery(email, username);
        userRepository.createUserQuery(email, username);
        verify(userRepository, times(1)).createUserQuery(email, username);
    }

    @Test
    void testGetUserId() {
        String email = "test@example.com";
        int expectedUserId = 1;
        when(userRepository.getUserId(email)).thenReturn(expectedUserId);
        int actualUserId = userRepository.getUserId(email);
        verify(userRepository, times(1)).getUserId(email);
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testFindUserExist() {
        String email = "test@example.com";
        String expectedUsername = "testuser";
        when(userRepository.findUserExist(email)).thenReturn(expectedUsername);
        String actualUsername = userRepository.findUserExist(email);
        verify(userRepository, times(1)).findUserExist(email);
        assertEquals(expectedUsername, actualUsername);
    }
}