package com.cocktail.controller;

import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping(params = "id")
    @ResponseBody
    public CocktailResponse findCocktail(Long id) {
        return cocktailService.findCocktail(id);
    }

    @PostMapping(value = "/new", params = "userId")
    public Long createCocktail(@Valid Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
        return cocktailService.createCocktail(userId, cocktailRequest);
    }
}
