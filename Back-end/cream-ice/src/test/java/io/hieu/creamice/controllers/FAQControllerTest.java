package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.FAQDTO;
import io.hieu.creamice.services.IFAQService;
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
public class FAQControllerTest {
    private MockMvc mvc;

    @Mock
    private IFAQService faqService;

    @InjectMocks
    public FAQController faqController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(faqController)
                .build();
    }

    @Test
    public void getFAQById_whenFAQExists_returnFAQ() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FAQDTO faq = FAQDTO.builder()
                .id(1L)
                .question("Question.")
                .answer("Answer.")
                .status("Status.")
                .build();
        String faqObjectAsJSON = objectMapper.writeValueAsString(faq);

        when(faqService.findById(1L))
                .thenReturn(Optional.of(faq));

        MockHttpServletResponse response = mvc.perform(
                get("/faqs/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(faqObjectAsJSON)));
    }

    @Test
    public void getFAQById_whenFAQDoesNotExists_returnNull() throws Exception {
        when(faqService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/faqs/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}