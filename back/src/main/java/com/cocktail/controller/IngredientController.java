package com.cocktail.controller;

import com.cocktail.domain.Ingredient;
import com.cocktail.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Ingredient> findById(@RequestParam Long id) {
        return ResponseEntity.of(ingredientService.findById(id));
    }


}
