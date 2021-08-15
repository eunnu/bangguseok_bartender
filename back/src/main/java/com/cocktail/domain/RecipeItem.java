package com.cocktail.domain;

import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "recipes")
public class RecipeItem {

    @Id @GeneratedValue
    @Column(name = "recipe_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne

    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private double quantity;

    @Override
    public String toString() {
        return "RecipeItem{\n" +
//                "cocktailId=" + cocktailId + "\n" +
                ", ingredient=" + ingredient + "\n" +
                ", quantity=" + quantity + "\n" +
                '}';
    }
}
