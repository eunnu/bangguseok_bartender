package com.cocktail.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "recipes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeItem {

    @Id @GeneratedValue
    @Column(name = "recipe_item_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    @NonNull
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private double quantity;

    @Override
    public String toString() {
        return "RecipeItem{\n" +
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

    @Builder
    public RecipeItem(@NonNull Cocktail cocktail, Ingredient ingredient, double quantity) {
        this.cocktail = cocktail;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }


    //==비즈니스 로직==//

}
