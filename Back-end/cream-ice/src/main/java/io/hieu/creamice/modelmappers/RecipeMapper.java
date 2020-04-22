package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.IceCream;
import io.hieu.creamice.beans.Recipe;
import io.hieu.creamice.dto.RecipeDTO;

public class RecipeMapper {
    public static RecipeDTO recipeToRecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO;
        recipeDTO = new RecipeDTO(recipe.getId(), recipe.getIceCream().getId(), recipe.getTitle()
                , recipe.getDescription(), recipe.getPrice(), recipe.getStatus(), recipe.getView_count()
                , recipe.getImage(), recipe.getDetails(), recipe.getCreated_at());
        return recipeDTO;
    }

    public static Recipe recipeDTOToRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setIceCream(new IceCream(recipeDTO.getIceCreamId()));
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setPrice(recipeDTO.getPrice());
        recipe.setStatus(recipeDTO.getStatus());
        recipe.setView_count(recipeDTO.getViewCount());
        recipe.setImage(recipeDTO.getImageInBase64());
        recipe.setDetails(recipeDTO.getDetails());
        recipe.setCreated_at(recipeDTO.getCreatedAt());
        return recipe;
    }
}