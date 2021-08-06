package com.cocktail.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Getter
@Table(name = "COCKTAILS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    private String description;

    @Embedded
    private Recipe recipe;

    @NonNull
    private String glass;

    private String created_user_id;

    @CreatedDate
    @Column(name = "create_date")
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private Timestamp updatedDate;

    @Builder
    public Cocktail(String name, String description, Recipe recipe, String glass) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.glass = glass;
    }
}
