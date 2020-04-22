package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Recipe;
import io.hieu.creamice.dto.RecipeDTO;
import io.hieu.creamice.repositories.IRecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {
    @Test
    public void find_findAll_recipesNotFound() {
        IRecipeRepository recipeRepository = mock(IRecipeRepository.class);

        when(recipeRepository.findAll()).thenReturn(Collections.emptyList());
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(recipeRepository);

        List<RecipeDTO> recipes = recipeServiceImpl.findAll();
        assertTrue(recipes.isEmpty());
    }


    @Test
    public void find_findAll_recipesFound() {
        IRecipeRepository recipeRepository = mock(IRecipeRepository.class);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(recipeRepository);

        List<RecipeDTO> recipesList = recipeServiceImpl.findAll();
        assertFalse(recipesList.isEmpty());
    }

    @Test
    public void find_findRecipeById_recipeFound() {
        final Long mockId = 1L;
        IRecipeRepository recipeRepository = mock(IRecipeRepository.class);
        Recipe recipe = new Recipe();

        when(recipeRepository.findById(mockId)).thenReturn(Optional.of(recipe));
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(recipeRepository);

        Optional<RecipeDTO> recipeDTO = recipeServiceImpl.findById(mockId);
        assertTrue(recipeDTO.isPresent());
    }

    @Test
    public void find_findRecipeById_recipeNotFound() {
        final Long mockId = 1L;
        IRecipeRepository recipeRepository = mock(IRecipeRepository.class);

        when(recipeRepository.findById(mockId)).thenReturn(Optional.empty());
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(recipeRepository);

        Optional<RecipeDTO> recipeDTO = recipeServiceImpl.findById(mockId);
        assertTrue(recipeDTO.isPresent());
    }
}