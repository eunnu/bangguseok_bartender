package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.QRecipeItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDSLRepository implements DynamicRepository {

    @Autowired
    private JPAQueryFactory query;

    public Page<Cocktail> findCocktailContainingIngredients(List<Long> ingredientId, Pageable pageable) {
        QRecipeItem qRecipeItem = QRecipeItem.recipeItem;

        BooleanBuilder builder = new BooleanBuilder();
        for (Long id : ingredientId) {
         builder.or(qRecipeItem.ingredient().id.eq(id));
        }
          QueryResults<Cocktail> results = query.select(qRecipeItem.cocktail()).from(qRecipeItem).where(builder)
                .groupBy(qRecipeItem.cocktail().id)
                .orderBy(qRecipeItem.cocktail().id.count().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
