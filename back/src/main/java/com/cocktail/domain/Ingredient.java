package com.cocktail.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Table(name = "ingredients")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Ingredient extends BaseEntity {
    @Column(name = "abv")
    private double abv;

    @Column(name = "description")
    private String description;

}
