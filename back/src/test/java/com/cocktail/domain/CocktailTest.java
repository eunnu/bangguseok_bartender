package com.cocktail.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CocktailTest {

    @Test
    public void test() {
        Cocktail cocktail = Cocktail.builder()
                .name("test")
                .description("test")
                .glass("test glass")
                .build();
        System.out.println(cocktail);
    }



}