package com.cocktail.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    private String name;

    private String description;

    private int abv;

    private String how;

    private String glass;

    private String created_user_id;

    @CreatedDate
    @Column(name = "create_date")
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private Timestamp updatedDate;
}
