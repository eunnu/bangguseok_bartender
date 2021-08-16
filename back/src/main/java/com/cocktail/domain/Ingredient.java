package com.cocktail.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Table(name = "ingredients")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    @NonNull
    @Column(name = "ingredient_name", unique = true)
    private String name;

    @Column(name = "abv")
    private double abv;

    @Column(name = "description")
    private String description;

    public Ingredient(Long id) {
        this.id = id;
    }

    //==생성 메서드==//
    @Builder
    public Ingredient(Long id, @NonNull String name, double abv, String description) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.description = description;
    }

}
