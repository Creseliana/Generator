package com.creseliana.generator.service;

import com.creseliana.generator.api.repository.UserRepository;
import com.creseliana.generator.dto.UserRegisterRequest;
import com.creseliana.generator.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BaseUserServiceTest {
    private final ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    @InjectMocks
    private BaseUserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        UserRegisterRequest newUser = new UserRegisterRequest();
        User user = new User();
        user.setUserId(1L);
        user.setUsername("test");

        when(mapper.map(newUser, User.class)).thenReturn(user);

        assertDoesNotThrow(() -> userService.registerUser(newUser));

        verify(userRepository).save(user);
        verify(userRepository).save(userCaptor.capture());
        verifyNoMoreInteractions(userRepository);

        User capturedUser = userCaptor.getValue();
        assertEquals(user.getUserId(), capturedUser.getUserId());
        assertEquals(user.getUsername(), capturedUser.getUsername());
        assertNotNull(capturedUser.getCreationDate());
        assertTrue(capturedUser.getLuckyNumber() > 0);
    }

    @Test
    void getLuckyUsers() {
        assertDoesNotThrow(() -> userService.getLuckyUsers());
        verify(userRepository).findByLuckyNumber(anyInt());
        verifyNoMoreInteractions(userRepository);
    }
}