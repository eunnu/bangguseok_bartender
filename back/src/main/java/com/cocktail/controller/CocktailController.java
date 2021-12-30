package com.cocktail.controller;

import com.cocktail.common.exception.BusinessException;
import com.cocktail.common.exception.NotFoundException;
import com.cocktail.domain.Cocktail;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.dto.CocktailResponse;
import com.cocktail.dto.ResponseMessage;
import com.cocktail.service.CocktailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

	@Autowired
	private CocktailService cocktailService;

	/* 칵테일 전체 조회 */
	@GetMapping
	public ResponseEntity<ResponseMessage> findAll(@PageableDefault Pageable pageable) {
		List<CocktailResponse> responses = cocktailService.findAll(pageable).stream().map(CocktailResponse::new).collect(Collectors.toList());

		return ResponseEntity.ok(new ResponseMessage(responses));
	}

	/* 칵테일 상세 조회 */
	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage> findCocktail(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseMessage(cocktailService.findCocktail(id).map(CocktailResponse::new).orElseThrow(NotFoundException::new)));
	}

	/* 칵테일 저장 */
	@PostMapping(value = "/new")
	public ResponseEntity<ResponseMessage> createCocktail(@RequestHeader("X-USER-ID") Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		Cocktail cocktail = cocktailService.createCocktail(userId, cocktailRequest);

		if (cocktail == null) throw new BusinessException();
		return ResponseEntity.ok(new ResponseMessage(cocktail));
	}

	/* 칵테일 수정 */
	@PutMapping("/{id}")
	public ResponseEntity<ResponseMessage> updateCocktail(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long id, @RequestBody @Valid CocktailRequest cocktailRequest) {
		cocktailService.updateCocktail(userId, id, cocktailRequest);
		return ResponseEntity.ok(ResponseMessage.noContent());
	}

	/* 이미지 저장 */
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

	/* 칵테일 삭제 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteCocktail(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long id) {
		cocktailService.deleteCocktail(userId, id);
		return ResponseEntity.noContent().build();
	}

	/* 이름이나 재료 리스트로 검색 */
	@GetMapping("search")
	public ResponseEntity<ResponseMessage> search(@PageableDefault Pageable pageable,
			@RequestParam(value = "name",required = false) String name, @RequestParam(value = "ingredientIds",required = false) List<Long> ingredientIds) {

		return ResponseEntity.ok(new ResponseMessage(cocktailService.search(name, ingredientIds, pageable)));
	}

	@AllArgsConstructor
	@Data
	public static class PathDTO implements Serializable {
		private String path;
	}

}
