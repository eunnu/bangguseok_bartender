package com.cocktail.service;

import com.cocktail.common.exception.UnauthorizedException;
import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Glass;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.repository.CocktailRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(readOnly = true)
public class CocktailServiceTest {

	@Resource
	private CocktailService cocktailService;
	@Resource
	private CocktailRepository CocktailRepositoryNew;

	@Test
	public void 칵테일_저장() throws Exception {
		// given
		List<Long> list1 = new ArrayList<>();
		list1.add(1L);

		List<Double> list2 = new ArrayList<>();
		list2.add(60.0);

		CocktailRequest cocktailRequest = new CocktailRequest();
		cocktailRequest.setDescription("마티니는 보드카 마티디와 진 마티니가 있다.");
		cocktailRequest.setGlass(Glass.COCKTAIL.toString());
		cocktailRequest.setName("Dry Martini");
		cocktailRequest.setIngredientIdList(list1);
		cocktailRequest.setQuantityList(list2);
		cocktailRequest.setTechnique("BUILD");
		cocktailRequest.setImageUri(null);
		// when
		Long cocktailId = cocktailService.createCocktail(1L, cocktailRequest);

		// then
		Cocktail cocktail1 = CocktailRepositoryNew.findById(cocktailId).get();

		// em.flush();
		assertThat(cocktailRequest.getDescription()).isEqualTo(cocktail1.getDescription());
		assertThat(cocktailRequest.getName()).isEqualTo(cocktail1.getName());
		assertThat(cocktail1.getId()).isNotNull();
	}

	@Test
	public void 단일조회() throws Exception {
		// given
		Long cocktailId = 2L;

		// when
		Cocktail cocktail = cocktailService.findCocktail(cocktailId).get();

		// then
		assertThat("Jack Coke").isEqualTo(cocktail.getName());
		// assertThat("admin").isEqualTo(cocktail.getUser().getName());
		// assertThat("Whiskey").isEqualTo(cocktail.getRecipeItems().get(0).getIngredient().getName());
	}

	@Test
	public void 수정() throws Exception {
	    // given
		Long userId = 1L;
		Long cocktailId = 2L;
		Cocktail cocktail1 = CocktailRepositoryNew.findById(cocktailId).get();
		CocktailRequest cocktailRequest = new CocktailRequest();
		cocktailRequest.setName(cocktail1.getName());
		cocktailRequest.setGlass(cocktail1.getGlass().name());
		cocktailRequest.setDescription(cocktail1.getDescription());
		cocktailRequest.setTechnique(cocktail1.getRecipe().getTechnique().name());
		cocktailRequest.getQuantityList().add(10.0);
		cocktailRequest.getIngredientIdList().add(1L);

		// when
		cocktailService.updateCocktail(userId, cocktailId, cocktailRequest);

		// then
		Cocktail modified = CocktailRepositoryNew.findById(cocktailId).get();
		assertThat(modified.getRecipe().getTechnique()).isEqualTo(cocktail1.getRecipe().getTechnique());
		assertThat(modified.getRecipe().getRecipeItems()).map(m -> m.getIngredient().getName()).doesNotContain("Whiskey");
		assertThat(modified.getRecipe().getRecipeItems()).map(m -> m.getIngredient().getName()).contains("Gin");
	}

	@Test
	public void 전체조회() throws Exception {
		// given
		Pageable pageable = PageRequest.of(1, 1);

		// when
		Page<Cocktail> cocktails = cocktailService.findAll(pageable);

		// then
		assertThat(cocktails.getSize()).isEqualTo(1);
		Cocktail cocktail = cocktails.toList().get(0);
		assertThat("Jack Coke").isEqualTo(cocktail.getName());
		assertThat(cocktail.getUserId()).isEqualTo(1);
		assertThat(cocktail.getRecipe().getRecipeItems().size()).isEqualTo(1);
	}

	@Test
	public void 삭제() throws Exception {
		// given
		Long cocktailId = 2L;
		Long userId = 1L;

		// when
		cocktailService.deleteCocktail(userId, cocktailId);

		// then
		Optional<Cocktail> cocktail = cocktailService.findCocktail(cocktailId);
		assertThat(cocktail.isPresent()).isEqualTo(false);
	}

	@Test
	public void 수정_다른유저() throws Exception {
		// given
		Long userId = 2L;
		Long cocktailId = 2L;
		Cocktail cocktail1 = CocktailRepositoryNew.findById(cocktailId).get();
		CocktailRequest cocktailRequest = new CocktailRequest();
		cocktailRequest.setName(cocktail1.getName());
		cocktailRequest.setGlass(cocktail1.getGlass().name());
		cocktailRequest.setDescription(cocktail1.getDescription());
		cocktailRequest.setTechnique(cocktail1.getRecipe().getTechnique().name());
		cocktailRequest.getQuantityList().add(10.0);
		cocktailRequest.getIngredientIdList().add(1L);

		// when
		Assertions.assertThrows(UnauthorizedException.class, () -> cocktailService.updateCocktail(userId, cocktailId, cocktailRequest));
		// then
	}

	@Test
	public void 삭제_다른유저() throws Exception {
		// given
		Long cocktailId = 2L;
		Long userId = 2L;

		// when
		Assertions.assertThrows(UnauthorizedException.class, () -> cocktailService.deleteCocktail(userId, cocktailId));

		// then
	}

	@Test
	public void 이름으로검색() throws Exception {
		// given
		String name = "Jack";

		// when

		// then
//		assertThat(cocktail.isPresent()).isEqualTo(false);

	}

	@Test
	public void 재료로검색() throws Exception {
		// given
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(3L);

		// when

		// then
	}

}