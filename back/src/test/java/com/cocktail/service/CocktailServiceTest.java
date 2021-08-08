package com.cocktail.service;

import com.cocktail.repository.CocktailRepository;
import org.junit.jupiter.api.Test;

class CocktailServiceTest {
    CocktailRepository cocktaliRepository;
    CocktailService cocktailService;

    @Test
    void testFindAll() {
        cocktaliRepository = new CocktailRepository();
        cocktailService = new CocktailService(cocktaliRepository);
        cocktailService.findAll();
    }

}