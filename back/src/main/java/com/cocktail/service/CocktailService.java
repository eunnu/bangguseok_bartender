package com.cocktail.service;

import com.cocktail.domain.Cocktail;
import com.cocktail.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> findAll() {
        return cocktailRepository.findAll();
    }

    public List<Cocktail> findByName(String name) {
        return cocktailRepository.findByName(name);
    }

    public Optional<Cocktail> findById(Long id) {
        return cocktailRepository.findById(id);
    }

}
