package com.cocktail.controller;

import com.cocktail.domain.Cocktail;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

	@Autowired
	private CocktailService cocktailService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<CocktailResponse>> findAll() {
		List<CocktailResponse> responses = new ArrayList<>();

		for (Cocktail cocktail : cocktailService.findAll()) {
			responses.add(new CocktailResponse(cocktail));
		}

		return ResponseEntity.ok(responses);
	}

	@GetMapping(params = "id")
	@ResponseBody
	public ResponseEntity<CocktailResponse> findCocktail(Long id) {
		Optional<Cocktail> cocktail = cocktailService.findCocktail(id);
		return ResponseEntity.of(cocktail.map(CocktailResponse::new));
	}

	@PostMapping(value = "/new", params = "userId")
	public ResponseEntity<Long> createCocktail(@Valid Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		Long cocktailId = cocktailService.createCocktail(userId, cocktailRequest);
		return ResponseEntity.ok(cocktailId);
	}

}
