package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.dto.UserInputDTO;
import io.hieu.creamice.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Test
    public void update_currentUserNotPresent_throwRuntimeException() {
        final Long mockId = 1L;
        IUserRepository userRepository = mock(IUserRepository.class);

        when(userRepository.findById(mockId))
                .thenReturn(Optional.empty());
        UserServiceImpl userServiceImplTest = new UserServiceImpl(userRepository);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userServiceImplTest.update(new UserInputDTO(), mockId);
        });
    }

    @Test
    public void update_currentUserPresentAndPasswordUnchanged_updateUserPasswordUnchanged() {
        final Long mockId = 1L;
        IUserRepository userRepository = mock(IUserRepository.class);
        User user = new User();
        user.setPassword("12345");

        when(userRepository.findById(mockId))
                .thenReturn(Optional.of(user));
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        UserInputDTO userInputDTO = new UserInputDTO();
        userInputDTO.setPassword("");
        UserDTO userDTO = userServiceImpl.update(userInputDTO, mockId);
        assertEquals(user.getPassword(), "12345");
    }

    @Test
    public void update_currentUserPresentAndPasswordChanged_updateUserPasswordChanged() {
        final Long mockId = 1L;
        IUserRepository userRepository = mock(IUserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        User user = new User();
        user.setPassword("12345");

        when(userRepository.findById(mockId))
                .thenReturn(Optional.of(user));
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        when(passwordEncoder.encode("abcd")).thenReturn("ABCD");
        userServiceImpl.setPasswordEncoder(passwordEncoder);

        UserInputDTO userInputDTO = new UserInputDTO();
        userInputDTO.setPassword("abcd");
        UserDTO userDTO = userServiceImpl.update(userInputDTO, mockId);
        assertEquals(user.getPassword(), "ABCD");
    }

    @Test
    public void find_findAll_usersNotFound() {
        IUserRepository userRepository = mock(IUserRepository.class);

        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        List<UserDTO> users = userServiceImpl.findAll();
        assertTrue(users.isEmpty());
    }


    @Test
    public void find_findAll_usersFound() {
        IUserRepository userRepository = mock(IUserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User());


        when(userRepository.findAll()).thenReturn(users);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        List<UserDTO> usersList = userServiceImpl.findAll();
        assertFalse(usersList.isEmpty());
    }

    @Test
    public void find_findUserById_userFound() {
        final Long mockId = 1L;
        IUserRepository userRepository = mock(IUserRepository.class);
        User user = new User();

        when(userRepository.findById(mockId)).thenReturn(Optional.of(user));
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        Optional<UserDTO> userDTO = userServiceImpl.findById(mockId);
        assertTrue(userDTO.isPresent());
    }

    @Test
    public void find_findUserById_userNotFound() {
        final Long mockId = 1L;
        IUserRepository userRepository = mock(IUserRepository.class);

        when(userRepository.findById(mockId)).thenReturn(Optional.empty());
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        Optional<UserDTO> userDTO = userServiceImpl.findById(mockId);
        assertTrue(userDTO.isPresent());
    }
}