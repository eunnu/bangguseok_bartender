package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CocktailRepository {

    @PersistenceContext
     EntityManager em;

    public Cocktail findById(Long id) {
        return em.find(Cocktail.class, id);
    }


}
