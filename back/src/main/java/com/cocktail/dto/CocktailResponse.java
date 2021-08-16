package com.cocktail.dto;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Glass;
import com.cocktail.domain.RecipeItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailResponse {

    private String name;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String description;

    private Set<RecipeItem> recipeItems = Collections.EMPTY_SET;

    private Glass glass;

    private Long userId;

    private String userName;

    private double abv = 0;

    public CocktailResponse(Cocktail cocktail) {
        this.name = cocktail.getName();
        this.description = cocktail.getDescription();
        this.abv = cocktail.getAbv();
        this.createdDate = cocktail.getCreatedDate();
        this.updatedDate = cocktail.getUpdatedDate();
        this.glass = cocktail.getGlass();
        this.userId = cocktail.getUser().getId();
        this.userName = cocktail.getUser().getName();
        this.recipeItems = cocktail.getRecipeItems();
    }

}
