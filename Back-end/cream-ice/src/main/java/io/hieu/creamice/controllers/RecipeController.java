package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.RecipeDTO;
import io.hieu.creamice.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class RecipeController {
    @Autowired
    private IRecipeService IRecipeService;

//    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
//    public ResponseEntity<List<RecipeDTO>> findAllRecipes() {
//        List<RecipeDTO> recipes = IRecipeService.findAll();
//        if (recipes.isEmpty()) {
//            return new ResponseEntity<>(recipes, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(recipes, HttpStatus.OK);
//    }

    @GetMapping(value = "/recipes")
    public Page<RecipeDTO> findAllRecipes(Pageable pageable) {
        return IRecipeService.findAll(pageable);
    }

    @GetMapping(value = "/recipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDTO> findRecipeById(@PathVariable("id") Long id) {
        Optional<RecipeDTO> recipe = IRecipeService.findById(id);
        if (recipe.isPresent()) {
            return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/filter-recipes")
    public ResponseEntity<RecipeDTO> findByIceCreamId(@RequestParam(name = "iceCreamId") Long iceCreamId) {
        List<RecipeDTO> recipes = IRecipeService.findByIceCreamId(iceCreamId);
        return new ResponseEntity(recipes, HttpStatus.OK);
    }

    @PostMapping(value = "/recipes")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        recipeDTO = IRecipeService.create(recipeDTO);
        return new ResponseEntity<>(recipeDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/recipes/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable("id") Long id, @RequestBody RecipeDTO recipeDTO) {
        recipeDTO = IRecipeService.update(recipeDTO, id);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id) {
        IRecipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}