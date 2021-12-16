package com.cocktail.repository;

import com.cocktail.domain.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select i from Ingredient i order by i.name")
    Page<Ingredient> findAll(Pageable pageable);
}
