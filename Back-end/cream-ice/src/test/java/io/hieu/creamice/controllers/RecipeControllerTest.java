package io.hieu.creamice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hieu.creamice.dto.RecipeDTO;
import io.hieu.creamice.services.IRecipeService;
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
public class RecipeControllerTest {
    private MockMvc mvc;

    @Mock
    private IRecipeService recipeService;

    @InjectMocks
    public RecipeController recipeController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(recipeController)
                .build();
    }

    @Test
    public void getRecipeById_whenRecipeExists_returnRecipe() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RecipeDTO recipe = RecipeDTO.builder()
                .id(1L)
                .iceCreamId(1L)
                .title("Demo Title")
                .description("No description.")
                .price(5.0)
                .status("In stock.")
                .viewCount(5)
                .imageInBase64("icecream.png")
                .details("No details.")
                .build();
        String recipeObjectAsJSON = objectMapper.writeValueAsString(recipe);

        when(recipeService.findById(1L))
                .thenReturn(Optional.of(recipe));

        MockHttpServletResponse response = mvc.perform(
                get("/recipes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(recipeObjectAsJSON)));
    }

    @Test
    public void getRecipeById_whenRecipeDoesNotExists_returnNull() throws Exception {
        when(recipeService.findById(1L))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/recipes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(response.getContentAsString(), is(equalTo("")));
    }
}