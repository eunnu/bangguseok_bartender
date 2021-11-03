package com.cocktail.controller;

import com.cocktail.domain.Cocktail;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

	@Autowired
	private CocktailService cocktailService;

	@GetMapping
	@ResponseBody
	public List<CocktailResponse> findAll() {
		List<CocktailResponse> responses = new ArrayList<>();

		for (Cocktail cocktail : cocktailService.findAll()) {
			responses.add(new CocktailResponse(cocktail));
		}

		return responses;
	}

	@GetMapping(params = "id")
	@ResponseBody
	public CocktailResponse findCocktail(Long id) {
		return new CocktailResponse(cocktailService.findCocktail(id));
	}

	@PostMapping(value = "/new", params = "userId")
	public Long createCocktail(@Valid Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		return cocktailService.createCocktail(userId, cocktailRequest);
	}

}
