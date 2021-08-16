package com.cocktail.repository;

import com.cocktail.domain.Ingredient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IngredientRepository {

    @PersistenceContext
    EntityManager em;

    public Ingredient findById(Long id) {
        return em.find(Ingredient.class, id);
    }
}
