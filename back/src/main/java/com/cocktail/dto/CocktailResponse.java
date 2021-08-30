package com.cocktail.dto;

import com.cocktail.common.Def;
import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Glass;
import com.cocktail.domain.Recipe;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CocktailResponse {

	private String name;

	private String createdDate;

	private String updatedDate;

	private String description;

	private Recipe recipe = Recipe.empty();

	private Glass glass;

	private Long userId;

	private String userName;

	private double abv = 0;

	public CocktailResponse(Cocktail cocktail) {
		this.name = cocktail.getName();
		this.description = cocktail.getDescription();
		this.createdDate = cocktail.getCreatedDate().format(Def.dateTimeFormatter);
		this.updatedDate = cocktail.getUpdatedDate().format(Def.dateTimeFormatter);
		this.glass = cocktail.getGlass();
		this.userId = cocktail.getUserId();
		// this.userName = cocktail.getUser().getName();
		this.recipe = cocktail.getRecipe();
		this.abv = recipe.getAbv();
	}

}
