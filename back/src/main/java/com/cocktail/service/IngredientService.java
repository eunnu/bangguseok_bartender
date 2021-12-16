package com.cocktail.service;

import com.cocktail.domain.Ingredient;
import com.cocktail.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    public Page<Ingredient> findAll(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

}
