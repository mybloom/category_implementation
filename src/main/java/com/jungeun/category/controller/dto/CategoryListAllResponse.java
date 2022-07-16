package com.jungeun.category.controller.dto;

import com.jungeun.category.domain.Category;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategoryListAllResponse {

	private final Long categoryId;
	private final String title;
	private final Long parentCategoryId;
	private List<CategoryListAllResponse> subCategories;

	public void setSubCategories(
		List<CategoryListAllResponse> subCategories) {
		this.subCategories = subCategories;
	}

	public static CategoryListAllResponse from(Category category) {
		return CategoryListAllResponse.builder()
			.categoryId(category.getId())
			.title(category.getTitle())
			.parentCategoryId(Optional.ofNullable(category.getParent())
				.orElse(Category.of(null))
				.getId())
			.build();
	}
}
