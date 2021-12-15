package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
//    @Query("select c from Cocktail c where c.name like :name")
    List<Cocktail> findByNameContaining(@Param("name") String name);

    @Query("select r.cocktail from RecipeItem r where r.ingredient.id = :ingredientId")
    List<Cocktail> findContainingIngredientId(@Param("ingredientId") Long ingredientId);

    Optional<Cocktail> findByIdAndUserId(Long id, Long userId);

    @Query("select c from Cocktail c order by c.createdDate desc")
    Page<Cocktail> findAllByCreatedDate(Pageable pageable);

}
