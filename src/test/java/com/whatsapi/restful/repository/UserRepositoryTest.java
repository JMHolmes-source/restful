package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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

        // Mock the behavior of createUserQuery
        doNothing().when(userRepository).createUserQuery(email, username);

        // Act
        userRepository.createUserQuery(email, username);

        // Assert
        // Verify that createUserQuery was called with the correct parameters
        verify(userRepository, times(1)).createUserQuery(email, username);
    }

    @Test
    void testGetUserId() {
        // Arrange
        String email = "test@example.com";
        int expectedUserId = 1;

        // Mock the behavior of getUserId
        when(userRepository.getUserId(email)).thenReturn(expectedUserId);

        // Act
        int actualUserId = userRepository.getUserId(email);

        // Assert
        // Verify that getUserId was called with the correct parameter
        verify(userRepository, times(1)).getUserId(email);

        // Verify the returned user ID
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testFindUserExist() {
        // Arrange
        String email = "test@example.com";
        String expectedUsername = "testuser";

        // Mock the behavior of findUserExist
        when(userRepository.findUserExist(email)).thenReturn(expectedUsername);

        // Act
        String actualUsername = userRepository.findUserExist(email);

        // Assert
        // Verify that findUserExist was called with the correct parameter
        verify(userRepository, times(1)).findUserExist(email);

        // Verify the returned username
        assertEquals(expectedUsername, actualUsername);
    }
}