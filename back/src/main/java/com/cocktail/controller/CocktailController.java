package com.cocktail.controller;

import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.dto.ResponseMessage;
import com.cocktail.exception.BusinessException;
import com.cocktail.exception.NotFoundException;
import com.cocktail.service.CocktailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

	@Autowired
	private CocktailService cocktailService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> findAll() {
		List<CocktailResponse> responses = cocktailService.findAll().stream().map(CocktailResponse::new).collect(Collectors.toList());

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

		if (cocktailId == 0) throw new BusinessException();
		return ResponseEntity.ok(new ResponseMessage(cocktailId));
	}

	@PutMapping("/{cocktailId}")
	public ResponseEntity<ResponseMessage> updateCocktail(@Valid Long userId, @PathVariable Long cocktailId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		cocktailService.updateCocktail(userId, cocktailId, cocktailRequest);
		return ResponseEntity.ok(ResponseMessage.noContent());
	}

	@PostMapping("/image")
	public ResponseEntity<ResponseMessage> uploadImage(@RequestParam("image") MultipartFile image) {
		String path;
		try {
			path = cocktailService.saveImage(image.getBytes());
		} catch (IOException e) {
			throw new BusinessException();
		}

		return ResponseEntity.ok(new ResponseMessage(new PathDTO(path)));
	}

	@AllArgsConstructor
	@Data
	public static class PathDTO implements Serializable {
		private String path;
	}

}
