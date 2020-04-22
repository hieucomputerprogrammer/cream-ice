package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.IceCreamDTO;
import io.hieu.creamice.services.IIceCreamService;
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

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class IceCreamControllerTest {
    private MockMvc mvc;

    @Mock
    private IIceCreamService iceCreamService;

    @InjectMocks
    public IceCreamController iceCreamController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(iceCreamController)
                .build();
    }

    @Test
    public void getIceCreamById_whenIceCreamExists_returnIceCream() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        IceCreamDTO iceCream = IceCreamDTO.builder()
                .id(1L)
                .name("Ice-cream")
                .description("Description.")
                .build();
        String iceCreamObjectAsJSON = objectMapper.writeValueAsString(iceCream);

        when(iceCreamService.findById(1L))
                .thenReturn(Optional.of(iceCream));

        MockHttpServletResponse response = mvc.perform(
                get("/iceCreams/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(iceCreamObjectAsJSON)));
    }

    @Test
    public void getIceCreamById_whenIceCreamDoesNotExists_returnNull() throws Exception {
        when(iceCreamService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/iceCreams/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}