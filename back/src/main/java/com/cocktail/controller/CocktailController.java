package com.cocktail.controller;

import com.cocktail.dto.CocktailResponse;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
