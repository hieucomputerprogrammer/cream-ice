package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIceCreamId(Long id);
}