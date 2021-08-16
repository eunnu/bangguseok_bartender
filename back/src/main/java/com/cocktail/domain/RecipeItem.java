package com.cocktail.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Builder
@Getter
@Entity
@Table(name = "recipes")
public class RecipeItem {

    @Id @GeneratedValue
    @Column(name = "recipe_item_id")
    private final Long id;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    @NonNull
    private Cocktail cocktail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private final double quantity;

    @Override
    public String toString() {
        return "RecipeItem{\n" +
//                "cocktailId=" + cocktailId + "\n" +
                ", ingredient=" + ingredient + "\n" +
                ", quantity=" + quantity + "\n" +
                '}';
    }

    //==연관관계 메서드==//
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    //==생성 메서드==//
//    public RecipeItem createRecipeItem(Cocktail cocktail, Ingredient ingredient, double quantity) { }

    //==비즈니스 로직==//

}
