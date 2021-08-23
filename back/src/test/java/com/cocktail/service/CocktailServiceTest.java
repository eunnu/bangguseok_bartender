package com.cocktail.service;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Glass;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.repository.CocktailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CocktailServiceTest {

    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 칵테일_저장() throws Exception {
        // given
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);

        List<Double> list2 = new ArrayList<>();
        list2.add(60.0);

        CocktailRequest cocktailRequest = new CocktailRequest();
        cocktailRequest.setDescription("마티니는 보드카 마티디와 진 마티니가 있다.");
        cocktailRequest.setGlass(Glass.COCKTAIL.toString());
        cocktailRequest.setName("Dry Martini");
        cocktailRequest.setIngredientIdList(list1);
        cocktailRequest.setQuantityList(list2);


        // when
        Long cocktailId = cocktailService.createCocktail(1L, cocktailRequest);

        // then
        Cocktail cocktail1 = cocktailRepository.findById(cocktailId);

//        em.flush();
        assertThat(cocktailRequest.getDescription()).isEqualTo(cocktail1.getDescription());
        assertThat(cocktailRequest.getName()).isEqualTo(cocktail1.getName());
//        assertThat(cocktailRequest.getIngredientIdList().get(0)).isEqualTo(List.copyOf(cocktail1.getRecipeItems()).get(0).getIngredient().getId());
//        assertThat(cocktailRequest.getQuantityList().get(0)).isEqualTo(cocktail1.getRecipeItems().get(0).getQuantity());
    }

    @Test
    public void 단일조회() throws Exception {
        // given
        Long cocktailId = 2L;

        // when
        CocktailResponse cocktail = cocktailService.findCocktail(cocktailId);

        // then
        assertThat("Jack Coke").isEqualTo(cocktail.getName());
//        assertThat("admin").isEqualTo(cocktail.getUser().getName());
//        assertThat("Whiskey").isEqualTo(cocktail.getRecipeItems().get(0).getIngredient().getName());
    }

}