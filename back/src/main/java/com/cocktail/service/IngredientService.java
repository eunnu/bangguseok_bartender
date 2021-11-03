package com.cocktail.service;

import com.cocktail.domain.Ingredient;
import com.cocktail.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }
}
