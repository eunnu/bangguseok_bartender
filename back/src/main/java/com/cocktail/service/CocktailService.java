package com.cocktail.service;

import com.cocktail.common.exception.NotFoundException;
import com.cocktail.domain.Cocktail;
import com.cocktail.domain.Ingredient;
import com.cocktail.domain.RecipeItem;
import com.cocktail.dto.CocktailRequest;
import com.cocktail.repository.CocktailRepository;
import com.cocktail.repository.IngredientRepository;
import com.cocktail.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CocktailService {

	private static final String FILE_FOLDER = "/home/img/";
	private static final Logger log = LoggerFactory.getLogger(CocktailService.class);

	@Autowired
	CocktailRepository cocktailRepository;
	@Autowired
	IngredientRepository ingredientRepository;
	@Autowired
	UUIDGenerator uuidGenerator;

	@Transactional
	public Long createCocktail(Long userId, CocktailRequest cocktailRequest) {
		Cocktail cocktail = cocktailRequest.toCocktail();
		cocktail.setUserId(userId);
		List<Long> ingredientIdList = cocktailRequest.getIngredientIdList();
		List<Double> quantityList = cocktailRequest.getQuantityList();

		for (int i = 0; i < ingredientIdList.size(); i++) {
			Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientIdList.get(i));

			cocktail.getRecipe().addRecipeItems(
					RecipeItem.builder().ingredient(ingredient.get())
							.quantity(quantityList.get(i)).cocktail(cocktail).build());
		}

		return cocktailRepository.save(cocktail).getId();
	}

	public Optional<Cocktail> findCocktail(Long id) {
		return cocktailRepository.findById(id);
	}

	public Page<Cocktail> findAll(Pageable pageable) {
		return cocktailRepository.findAllByCreatedDate(pageable);
	}

	@Transactional
	public void updateCocktail(Long userId, Long cocktailId, CocktailRequest cocktailRequest) {
		Optional<Cocktail> originCocktail = cocktailRepository.findByIdAndUserId(cocktailId, userId);
		if (!originCocktail.isPresent()) throw new NotFoundException();
		Cocktail toCocktail = cocktailRequest.toCocktail();
		for (RecipeItem i : toCocktail.getRecipe().getRecipeItems())
			i.setIngredient(ingredientRepository.getById(i.getIngredient().getId()));

		originCocktail.get().edit(toCocktail);
	}

	public String saveImage(byte[] image) throws IOException {
		String name = uuidGenerator.getUUID() + ".png";
		Path dirPath = Paths.get(FILE_FOLDER);
		if (!Files.exists(dirPath)) Files.createDirectories(dirPath);
		Path path = Paths.get(FILE_FOLDER + name);
		Files.write(path, image);
		return path.toString();
	}

}
