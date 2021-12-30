package com.cocktail.controller;

import com.cocktail.domain.Technique;
import com.cocktail.dto.CocktailResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@Transactional
public class CocktailControllerTest {

    @Autowired
    CocktailController cocktailController;

    @Test
    public void 단일조회() throws Exception {
        // given
        Long id = 19L;

        // when
        CocktailResponse cocktail = cocktailController.findCocktail(id);

        // then
        System.out.println("cocktail = " + cocktail);

        assertThat("Tom Collins").isEqualTo(cocktail.getName());
        assertThat(Technique.BUILD).isEqualTo(cocktail.getRecipe().getTechnique());
    }

}