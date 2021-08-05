package com.cocktail.domain;

import com.cocktail.model.AlcoholType;
import com.cocktail.model.Ingredient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Table(name = "ALCOHOLS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// TODO: etc와 공통되는 부분 추상 클래스로 빼기
public class Alcohol implements Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    private int abv;

    private String description;

}
