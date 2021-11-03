package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

//@RequiredArgsConstructor
@Repository
public class CocktailRepository {

	@PersistenceContext
	EntityManager em;

	public Cocktail findById(Long id) {
		return em.find(Cocktail.class, id);
	}

	public Long save(Cocktail cocktail) {
		cocktail.setCreatedDate(LocalDateTime.now());
		cocktail.setUpdatedDate(LocalDateTime.now());

		em.persist(cocktail);
		return cocktail.getId();
	}

	public List<Cocktail> findAll() {
		return em.createQuery("select c from Cocktail c", Cocktail.class).getResultList();
	}

	public List<Cocktail> findByNameContaining(String name) {
		TypedQuery<Cocktail> query = em.createQuery("select c from Cocktail c where c.name like :name", Cocktail.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	public List<Cocktail> findContainingIngredientId(Long ingredientId) {
		Query query = em.createQuery("select r.cocktail from RecipeItem r where r.ingredient.id = :ingredient",
				Cocktail.class);
		query.setParameter("ingredient", ingredientId);

		return query.getResultList();
	}

}
