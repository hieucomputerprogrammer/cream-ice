package io.hieu.creamice.services;

import io.hieu.creamice.dto.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {
    List<RecipeDTO> findAll();

    List<RecipeDTO> findByIceCreamId(Long iceCreamId);

    Optional<RecipeDTO> findById(Long id);

    RecipeDTO create(RecipeDTO recipeDTO);

    RecipeDTO update(RecipeDTO recipeDTO, Long id);

    Page<RecipeDTO> findAll(Pageable pageable);

    void delete(Long id);
}