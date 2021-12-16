package com.cocktail.dto;

import com.cocktail.domain.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class CocktailRequest {

	private String name;

	private String description;

	private List<Long> ingredientIdList = new ArrayList<>();

	private List<Double> quantityList = new ArrayList<>();

	private String glass;

	private String technique;

	private String imageUri;

	public Cocktail toCocktail() {
		Cocktail build = Cocktail.builder().name(name).description(description).glass(Glass.valueOf(glass)).imagePath(imageUri).build();
		Recipe recipe = new Recipe();
		recipe.setTechnique(Technique.valueOf(technique));
		for (int i = 0; i < ingredientIdList.size(); i++) {
			recipe.addRecipeItems(
					RecipeItem.builder()
							.ingredient(Ingredient.builder().id(ingredientIdList.get(i)).build())
							.quantity(quantityList.get(i))
							.build()
			);
		}
		build.setRecipe(recipe);

		return build;
	}

}
