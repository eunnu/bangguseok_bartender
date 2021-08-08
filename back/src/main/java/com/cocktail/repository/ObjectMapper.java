package com.cocktail.repository;

import com.cocktail.domain.Cocktail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper {

    public static Cocktail convertResultSetToCocktail(Object obj) throws SQLException {
        if(obj == null) return null;

        ResultSet rs = (ResultSet) obj;
        return Cocktail.builder()
                .name(rs.getNString("name"))
                .description(rs.getNString("description"))
                .glass(rs.getNString("glass"))
                .build();
    }
}
