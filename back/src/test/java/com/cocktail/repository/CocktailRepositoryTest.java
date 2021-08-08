package com.cocktail.repository;

import com.cocktail.Config;
import com.cocktail.domain.Cocktail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.security.RunAs;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CocktailRepositoryTest {

    @Autowired
    CocktailRepository cocktailRepository;

    @Test
    void findAll() {
        List<Cocktail> cocktailList = cocktailRepository.findAll();
        assertNotNull(cocktailList);
    }

    @Test
    void findById() {
        Optional<Cocktail> cocktail = cocktailRepository.findById(1L);
        assertNotNull(cocktail);
    }

    @Test
    void findByName() {
        List<Cocktail> cocktail = cocktailRepository.findByName("coke");
        assertNotNull(cocktail);
        assertNotEquals(Collections.EMPTY_LIST, cocktail);
    }

}