package com.cocktail.domain;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Table(name = "cocktails")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cocktail extends TimeEntity {

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cocktail")
    private List<RecipeItem> recipeItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Glass glass;

    @ManyToOne
    @JoinColumn(name = "create_user_id")
    private User user;

    @Transient
    private double abv;

    public double getAbv() {
        if(abv == 0) {
            double alcoholSum = 0, totalSum = 0;
            for (RecipeItem item : recipeItems) {
                alcoholSum += item.getQuantity() * item.getIngredient().getAbv();
                totalSum += item.getQuantity();
            }
            abv = alcoholSum / totalSum;
        }
        return abv;
    }
}
