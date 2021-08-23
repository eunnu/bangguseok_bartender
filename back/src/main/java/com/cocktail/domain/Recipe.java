package com.cocktail.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Recipe {
    @JsonManagedReference
    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL)
    @NonNull
    private final Set<RecipeItem> recipeItems = new HashSet<>();

    @Column(name = "technique")
    @Enumerated(EnumType.STRING)
    private Technique technique;

    @Transient
    private double abv = 0;

    //==연관관계 메서드==//
    public void addRecipeItems(RecipeItem recipeItem) {
        this.recipeItems.add(recipeItem);
    }

    //==생성 메서드==//
    public static Recipe empty() {
        return new Recipe();
    }


    //==비즈니스 로직==//
    public double getAbv() {
        if(abv == 0) {
            double alcoholSum = 0, totalSum = 0;
            for (RecipeItem item : recipeItems) {
                alcoholSum += item.getQuantity() * item.getIngredient().getAbv();
                totalSum += item.getQuantity();
            }
            abv = alcoholSum / totalSum;
        }
        return abv;
    }
}
