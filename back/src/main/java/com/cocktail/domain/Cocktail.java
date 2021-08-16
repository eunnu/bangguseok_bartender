package com.cocktail.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Table(name = "cocktails")
@NoArgsConstructor
@Entity
public class Cocktail {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @CreatedDate
    @Column(name = "create_date")
    @Setter
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "update_date")
    @Setter
    private LocalDateTime updatedDate = LocalDateTime.now();

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL)
    @NonNull
    private final List<RecipeItem> recipeItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Glass glass;

    @ManyToOne
    @JoinColumn(name = "create_user_id")
    @NonNull
    private User user;

    @Transient
    private double abv = 0;

    //==연관관계 메서드==//
    public void addRecipeItems(RecipeItem recipeItem) {
        this.recipeItems.add(recipeItem);
    }

    public void setUser(User user) {
        this.user = user;
    }

    //==생성 메서드==//
    @Builder
    public Cocktail(Long id, @NonNull String name, String description, Glass glass, double abv) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.recipeItems = recipeItems;
        this.glass = glass;
//        this.user = user;
        this.abv = abv;
    }

//    public Cocktail createCocktail(User user, RecipeItem... recipeItem)

    //==비즈니스 로직==//
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
