package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.FeedbackDTO;
import io.hieu.creamice.services.IFeedbackService;
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
public class FeedbackControllerTest {
    private MockMvc mvc;

    @Mock
    private IFeedbackService feedbackService;

    @InjectMocks
    public FeedbackController feedbackController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(feedbackController)
                .build();
    }

    @Test
    public void getFeedbackById_whenFeedbackExists_returnFeedback() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FeedbackDTO feedback = FeedbackDTO.builder()
                .id(1L)
                .customerID(1L)
                .details("Details")
                .createDate(new Date())
                .build();
        String feedbackObjectAsJSON = objectMapper.writeValueAsString(feedback);

        when(feedbackService.findById(1L))
                .thenReturn(Optional.of(feedback));

        MockHttpServletResponse response = mvc.perform(
                get("/feedbacks/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(feedbackObjectAsJSON)));
    }

    @Test
    public void getFeedbackById_whenFeedbackDoesNotExists_returnNull() throws Exception {
        when(feedbackService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/feedbacks/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}