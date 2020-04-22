package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Recipe;
import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.RecipeDTO;
import io.hieu.creamice.modelmappers.RecipeMapper;
import io.hieu.creamice.repositories.IIceCreamRepository;
import io.hieu.creamice.repositories.IRecipeRepository;
import io.hieu.creamice.repositories.IUserRepository;
import io.hieu.creamice.security.jwt.JwtUserDetails;
import io.hieu.creamice.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements IRecipeService {
    @Autowired
    private IRecipeRepository IRecipeRepository;
    @Autowired
    private IUserRepository IUserRepository;
    @Autowired
    private IIceCreamRepository IIceCreamRepository;

    @Autowired
    public RecipeServiceImpl(IRecipeRepository IRecipeRepository) {
        this.IRecipeRepository = IRecipeRepository;
    }

    @Override
    public List<RecipeDTO> findAll() {
        List<Recipe> recipes = IRecipeRepository.findAll();
        List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipe recipe : recipes) {
            recipeDTOs.add(RecipeMapper.recipeToRecipeDTO(recipe));
        }
        return recipeDTOs;
    }

    @Override
    public List<RecipeDTO> findByIceCreamId(Long iceCreamId) {
        List<Recipe> recipes = IRecipeRepository.findByIceCreamId(iceCreamId);
        List<RecipeDTO> recipeDTOs = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipeDTOs.add(RecipeMapper.recipeToRecipeDTO(recipe));
        }
        return recipeDTOs;
    }

    @Override
    public Page<RecipeDTO> findAll(Pageable pageable) {
        Page<Recipe> recipesPage = IRecipeRepository.findAll(pageable);
        Page<RecipeDTO> recipeDTOsPage = recipesPage.map(recipe -> RecipeMapper.recipeToRecipeDTO(recipe));
        return recipeDTOsPage;
    }

    @Override
    public Optional<RecipeDTO> findById(Long id) {
        return IRecipeRepository.findById(id)
                .map(recipe -> RecipeMapper.recipeToRecipeDTO(recipe));
    }



    @Override
    public RecipeDTO create(RecipeDTO recipeDTO) {
        Recipe recipe = RecipeMapper.recipeDTOToRecipe(recipeDTO);
        recipe = IRecipeRepository.save(recipe);
        return RecipeMapper.recipeToRecipeDTO(recipe);
    }

    @Override
    public RecipeDTO update(RecipeDTO recipeDTO, Long id) {
        Optional<Recipe> currentRecipe = IRecipeRepository.findById(id);

        if (!currentRecipe.isPresent()) {
            throw new RuntimeException("No recipe ID " + id + " found!");
        }
        JwtUserDetails jwtUserDetails = (JwtUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Recipe recipe = currentRecipe.get();
        User currentUser = IUserRepository.findByUsername(jwtUserDetails.getUsername());
        recipe.setUser(currentUser);
        recipe.setIceCream(IIceCreamRepository.getOne(recipeDTO.getIceCreamId()));
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setPrice(recipeDTO.getPrice());
        recipe.setStatus(recipeDTO.getStatus());
        recipe.setView_count(recipeDTO.getViewCount());
        recipe.setImage(recipeDTO.getImageInBase64());
        recipe.setDetails(recipeDTO.getDetails());
        recipe.setCreated_at(recipeDTO.getCreatedAt());

        return RecipeMapper.recipeToRecipeDTO(recipe);
    }

    @Override
    public void delete(Long id) {
        IRecipeRepository.deleteById(id);
    }
}