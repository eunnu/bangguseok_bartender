package com.cocktail.repository;

import com.cocktail.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

	@PersistenceContext
	EntityManager em;

	public User findById(Long userId) {
		return em.find(User.class, userId);
	}

}
