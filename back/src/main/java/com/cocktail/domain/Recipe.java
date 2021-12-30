package com.cocktail.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@Data
public class Recipe {

	@JsonManagedReference
	@OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NonNull
	private Set<RecipeItem> recipeItems = new HashSet<>();

	@Column(name = "technique")
	@Enumerated(EnumType.STRING)
	private Technique technique;

	@Transient
	private double abv = 0;

	// ==연관관계 메서드==//
	public void addRecipeItems(RecipeItem recipeItem) {
		this.recipeItems.add(recipeItem);
	}

	// ==생성 메서드==//
	public static Recipe empty() {
		return new Recipe();
	}

	// ==비즈니스 로직==//
	public double getAbv() {
		if (abv == 0) {
			double alcoholSum = 0, totalSum = 0;
			for (RecipeItem item : recipeItems) {
				if (item.getQuantity() < 5) continue;
				alcoholSum += item.getQuantity() * item.getIngredient().getAbv();
				totalSum += item.getQuantity();
			}
			int intAbv = (int) ((alcoholSum / totalSum) * 100);
			abv = intAbv / 100;
		}
		return abv;
	}

	public void edit(Recipe modified) {
		this.technique = modified.getTechnique();
		this.recipeItems = modified.getRecipeItems();
	}
}
