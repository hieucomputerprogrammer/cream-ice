package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.OrderDTO;
import io.hieu.creamice.services.IOrderService;
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
public class OrderControllerTest {
    private MockMvc mvc;

    @Mock
    private IOrderService orderService;

    @InjectMocks
    public OrderController orderController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(orderController)
                .build();
    }

    @Test
    public void getOrderById_whenOrderExists_returnOrder() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO order = OrderDTO.builder()
                .id(1L)
                .userId(1L)
                .paymentOption("Cash")
                .paymentId(1L)
                .createDate(new Date())
                .deliveryDetail("Ground")
                .notes("No notes.")
                .status("Delivered.")
                .build();
        String userObjectAsJSON = objectMapper.writeValueAsString(order);

        when(orderService.findById(1L))
                .thenReturn(Optional.of(order));

        MockHttpServletResponse response = mvc.perform(
                get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(userObjectAsJSON)));
    }

    @Test
    public void getOrderById_whenOrderDoesNotExists_returnNull() throws Exception {
        when(orderService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}