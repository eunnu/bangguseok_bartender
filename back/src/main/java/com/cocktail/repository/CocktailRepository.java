package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

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

}
