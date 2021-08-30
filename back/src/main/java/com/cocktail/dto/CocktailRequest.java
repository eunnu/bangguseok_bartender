package com.cocktail.dto;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Glass;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class CocktailRequest {

	private String name;

	private String description;

	private List<Long> ingredientIdList = new ArrayList<>();

	private List<Double> quantityList = new ArrayList<>();

	private String glass;

	private Long userId;

	public Cocktail toCocktail() {
		return Cocktail.builder().name(name).description(description).glass(Glass.valueOf(glass)).build();
	}

}
