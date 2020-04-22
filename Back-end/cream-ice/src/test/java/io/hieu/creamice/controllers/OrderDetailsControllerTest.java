package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.OrderDetailsDTO;
import io.hieu.creamice.services.IOrderDetailsService;
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
public class OrderDetailsControllerTest {
    private MockMvc mvc;

    @Mock
    private IOrderDetailsService orderDetailsService;

    @InjectMocks
    public OrderDetailsController orderDetailsController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(orderDetailsController)
                .build();
    }

    @Test
    public void getOrderDetailsById_whenOrderDetailsExists_returnOrderDetails() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDetailsDTO orderDetails = OrderDetailsDTO.builder()
                .id(1L)
                .orderId(1L)
                .recipeId(1L)
                .quantity(1)
                .price(5.0)
                .notes("No notes.")
                .build();
        String orderDetailsObjectAsJSON = objectMapper.writeValueAsString(orderDetails);

        when(orderDetailsService.findById(1L))
                .thenReturn(Optional.of(orderDetails));

        MockHttpServletResponse response = mvc.perform(
                get("/orderDetails/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(orderDetailsObjectAsJSON)));
    }

    @Test
    public void getOrderDetailsById_whenOrderDetailsDoesNotExists_returnNull() throws Exception {
        when(orderDetailsService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/orderDetails/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}