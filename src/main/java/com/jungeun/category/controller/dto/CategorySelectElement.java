package com.jungeun.category.controller.dto;

import com.jungeun.category.domain.Category;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategorySelectElement {

	private Long categoryId;
	private String title;
	private Long parentCategoryId;

	public static CategorySelectElement from(Category category) {
		return CategorySelectElement.builder()
			.categoryId(category.getId())
			.title(category.getTitle())
			.parentCategoryId(Optional.ofNullable(category.getParent())
				.orElse(Category.of(null))
				.getId())
			.build();
	}
}
