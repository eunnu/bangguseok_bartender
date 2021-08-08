package com.cocktail.controller;

import com.cocktail.domain.Cocktail;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> findAll() {
        return cocktailService.findAll();
    }

    @GetMapping(params = "name")
    public List<Cocktail> findByName(String name) {
        return cocktailService.findByName(name);
    }

    @GetMapping(params = "id")
    public Optional<Cocktail> findById(Long id) {
        return cocktailService.findById(id);
    }
}
