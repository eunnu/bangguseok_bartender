package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    @Query("select c from Cocktail c where c.name like :name")
    List<Cocktail> findByNameContaining(@Param("name") String name);

    @Query("select r.cocktail from RecipeItem r where r.ingredient.id = :ingredientId")
    List<Cocktail> findContainingIngredientId(@Param("ingredientId") Long ingredientId);

}
