package com.cocktail.controller;

import com.cocktail.common.exception.BusinessException;
import com.cocktail.common.exception.NotFoundException;
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
	@GetMapping(params = "id")
	public ResponseEntity<ResponseMessage> findCocktail(Long id) {
		return ResponseEntity.ok(new ResponseMessage(cocktailService.findCocktail(id).map(CocktailResponse::new).orElseThrow(NotFoundException::new)));
	}

	/* 칵테일 저장 */
	@PostMapping(value = "/new", params = "userId")
	public ResponseEntity<ResponseMessage> createCocktail(@Valid Long userId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		Long cocktailId = cocktailService.createCocktail(userId, cocktailRequest);

		if (cocktailId == 0) throw new BusinessException();
		return ResponseEntity.ok(new ResponseMessage(cocktailId));
	}

	/* 칵테일 수정 */
	@PutMapping("/{cocktailId}")
	public ResponseEntity<ResponseMessage> updateCocktail(@Valid Long userId, @PathVariable Long cocktailId, @RequestBody @Valid CocktailRequest cocktailRequest) {
		cocktailService.updateCocktail(userId, cocktailId, cocktailRequest);
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
	@DeleteMapping(params = "id")
	public ResponseEntity<ResponseMessage> deleteCocktail() {

		return ResponseEntity.noContent().build();
	}

	/* 이름으로 검색 */
	@GetMapping("search")
	public ResponseEntity<ResponseMessage> search(@PageableDefault Pageable pageable, @RequestParam Map<String,String> queryMap) {

		return ResponseEntity.noContent().build();
	}

	@AllArgsConstructor
	@Data
	public static class PathDTO implements Serializable {
		private String path;
	}

}
