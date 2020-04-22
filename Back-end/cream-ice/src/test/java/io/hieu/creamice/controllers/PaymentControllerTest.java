package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.PaymentDTO;
import io.hieu.creamice.services.IPaymentService;
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
public class PaymentControllerTest {
    private MockMvc mvc;

    @Mock
    private IPaymentService paymentService;

    @InjectMocks
    public PaymentController paymentController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(paymentController)
                .build();
    }

    @Test
    public void getPaymentById_whenPaymentExists_returnPayment() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentDTO payment = PaymentDTO.builder()
                .id(1L)
                .cardType("Credit Card")
                .cardNumber(5555)
                .cvv(555)
                .nameOnCard("Hieu Minh Le")
                .expiredDate(new Date())
                .dateOfBirth(new Date())
                .build();
        String paymentObjectAsJSON = objectMapper.writeValueAsString(payment);

        when(paymentService.findById(1L))
                .thenReturn(Optional.of(payment));

        MockHttpServletResponse response = mvc.perform(
                get("/payments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(paymentObjectAsJSON)));
    }

    @Test
    public void getPaymentById_whenPaymentDoesNotExists_returnNull() throws Exception {
        when(paymentService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/payments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}