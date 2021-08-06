package com.cocktail.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "RECIPES")

public class Recipe {

    @ManyToOne
    private int cocktail_id;

    private int ingredient_id;

    private double abv;
}
