package com.cocktail.repository;

import com.cocktail.domain.Cocktail;
import com.cocktail.domain.QRecipeItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDSLRepository implements DynamicRepository {

    @Autowired
    private JPAQueryFactory query;

    public List<Cocktail> findContainingIngredients(Long... ingredientId) {
        QRecipeItem qRecipeItem = QRecipeItem.recipeItem;

        BooleanBuilder builder = new BooleanBuilder();
        for (Long id : ingredientId) {
            builder.or(qRecipeItem.ingredient().id.eq(id));
        }

        return query
                .select(qRecipeItem.cocktail())
                .distinct()
                .from(qRecipeItem)
                .where(builder).fetch();
    }

}
