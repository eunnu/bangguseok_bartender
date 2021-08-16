package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CocktailRepositoryTest {

    @Autowired
    CocktailRepository cocktailRepository;

    @Transactional
    @Test
    public void 저장() throws Exception {
        Cocktail cocktail = Cocktail.builder().name("cocktailA").build();

        Long savedId = cocktailRepository.save(cocktail);

        Cocktail resultedCocktail = cocktailRepository.findById(savedId);

        org.junit.jupiter.api.Assertions.assertNotNull(resultedCocktail);
        Assertions.assertThat(cocktail.getName()).isEqualTo(resultedCocktail.getName());
    }


}