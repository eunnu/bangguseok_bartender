package com.cocktail.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Embedded
    private Recipe recipe = Recipe.empty();

    @Enumerated(EnumType.STRING)
    private Glass glass;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER 면 left outer join 해옴
    @JoinColumn(name = "create_user_id")
    @NonNull
    private User user;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //==생성 메서드==//
    @Builder
    public Cocktail(Long id, @NonNull String name, String description, Glass glass) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.glass = glass;
    }

}
