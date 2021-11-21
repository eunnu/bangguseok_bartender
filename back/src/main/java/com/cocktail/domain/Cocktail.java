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

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "create_user_id")
	@Setter
	@NonNull
	private Long userId;

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	// ==생성 메서드==//
	@Builder
	public Cocktail(Long id, String name, String description, Glass glass, String imagePath) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.glass = glass;
		this.imagePath = imagePath;
	}

	// ==비즈니스 메서드==//
	public Cocktail edit(Cocktail modified) {
		this.name = modified.getName();
		this.description = modified.getDescription();
		this.glass = modified.getGlass();
		this.recipe.edit(modified.getRecipe());

		return modified;
	}

}
