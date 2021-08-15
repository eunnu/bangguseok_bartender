package com.cocktail.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class RecipeId implements Serializable {
    @Column(name = "cocktail_id")
    @EqualsAndHashCode.Include
    private Long cocktailId;
    @EqualsAndHashCode.Include
    @Column(name = "ingredient_id")
    private Long ingredientId;
}
