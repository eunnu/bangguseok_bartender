package com.cocktail.service;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.RecipeItem;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.repository.CocktailRepository;
import com.cocktail.repository.IngredientRepository;
import com.cocktail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CocktailService {

    @Autowired
    CocktailRepository cocktailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Transactional
    public Long createCocktail(Long userId, CocktailRequest cocktailRequest) {
        Cocktail cocktail = cocktailRequest.toCocktail();
        cocktail.setUser(userRepository.findById(userId));
        List<Long> ingredientIdList = cocktailRequest.getIngredientIdList();
        List<Double> quantityList = cocktailRequest.getQuantityList();

        for (int i = 0; i < ingredientIdList.size(); i++) {
            cocktail.addRecipeItems(
                    RecipeItem.builder()
                            .ingredient(ingredientRepository.findById(ingredientIdList.get(i)))
                            .quantity(quantityList.get(i))
                            .cocktail(cocktail)
                            .build()
            );
        }

        return cocktailRepository.save(cocktail);
    }

    public CocktailResponse findCocktail(Long id) {
        return new CocktailResponse(cocktailRepository.findById(id));
    }

}
