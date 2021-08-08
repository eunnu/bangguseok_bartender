package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CocktailRepository {

    private static final Logger logger = LoggerFactory.getLogger(CocktailRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cocktail> findAll() {
        return new ArrayList<>(jdbcTemplate
                .query("SELECT * FROM COCKTAILS;", (rs, rowNum) -> ObjectMapper.convertResultSetToCocktail(rs)));
    }

    public Optional<Cocktail> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate
                .queryForObject("SELECT * FROM COCKTAILS WHERE id = ?",
                        (rs, rowNum) -> ObjectMapper.convertResultSetToCocktail(rs)
                        , id
                ));
    }

    public List<Cocktail> findByName(String name) {
        return new ArrayList<>(jdbcTemplate
                .query("SELECT * FROM COCKTAILS WHERE LOWER(name) like concat('%', ?, '%')",
                        (rs, rowNum) -> ObjectMapper.convertResultSetToCocktail(rs)
                        , name
                ));
    }
}
