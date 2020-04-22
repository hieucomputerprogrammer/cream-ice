package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mvc;

    @Mock
    private IUserService userService;

    @InjectMocks
    public UserController userController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void getUserById_whenUserExists_returnUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO user = UserDTO.builder()
                .id(1L)
                .username("hieu")
                .name("Hieu Minh Le")
                .gender("Male")
                .dateOfBirth(new Date())
                .address("Viet Nam")
                .phoneNumber("12345")
                .email("hieu@outlook.com")
                .avatarInBase64("hieu.png")
                .status("Active")
                .build();
        String userObjectAsJSON = objectMapper.writeValueAsString(user);

        when(userService.findById(1L))
                .thenReturn(Optional.of(user));

        MockHttpServletResponse response = mvc.perform(
                get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(userObjectAsJSON)));
    }

    @Test
    public void getUserById_whenUserDoesNotExists_returnNull() throws Exception {
        when(userService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}