package com.cocktail.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "RECIPES")

public class Recipe {

    @ManyToOne
    private Long cocktail_id;

    private Long ingredient_id;

    private double abv;
}
