package com.cocktail.controller;

import com.cocktail.domain.Cocktail;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.dto.ResponseMessage;
import com.cocktail.exception.BusinessException;
import com.cocktail.exception.NotFoundException;
import com.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ResponseMessage> findAll() {
		List<CocktailResponse> responses = new ArrayList<>();

		for (Cocktail cocktail : cocktailService.findAll()) {
			responses.add(new CocktailResponse(cocktail));
		}

		return ResponseEntity.ok(new ResponseMessage(responses));
	}

	@GetMapping(params = "id")
	@ResponseBody
	public ResponseEntity<ResponseMessage> findCocktail(Long id) {
		return ResponseEntity.ok(new ResponseMessage(cocktailService.findCocktail(id).map(CocktailResponse::new).orElseThrow(NotFoundException::new)));
	}

	@PostMapping(value = "/new", params = "userId")
	public ResponseEntity<ResponseMessage> createCocktail(@Valid Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		Long cocktailId = cocktailService.createCocktail(userId, cocktailRequest);

		if(cocktailId == 0) throw new BusinessException();
		return ResponseEntity.ok(new ResponseMessage(cocktailId));
	}

}
