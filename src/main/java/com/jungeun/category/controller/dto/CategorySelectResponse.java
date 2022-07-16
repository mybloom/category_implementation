package com.jungeun.category.controller.dto;

import com.jungeun.category.domain.Category;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategorySelectResponse {

	private final Long categoryId;
	private final String title;
	private final Long parentCategoryId;
	private List<CategorySelectResponse> subCategories;

	public void setSubCategories(
		List<CategorySelectResponse> subCategories) {
		this.subCategories = subCategories;
	}

	public static CategorySelectResponse from(Category category) {
		return CategorySelectResponse.builder()
			.categoryId(category.getId())
			.title(category.getTitle())
			.parentCategoryId(Optional.ofNullable(category.getParent())
				.orElse(Category.of(null))
				.getId())
			.build();
	}

	public static CategorySelectResponse from(ICategoryJoin category) {
		return CategorySelectResponse.builder()
			.categoryId(category.getCategoryId())
			.title(category.getTitle())
			.parentCategoryId(category.getParentCategoryId())
			.build();
	}

	public static CategorySelectResponse of(Long categoryId,
		String title, Long parentCategoryId, List<CategorySelectResponse> subCategories) {
		return CategorySelectResponse.builder()
			.categoryId(categoryId)
			.title(title)
			.parentCategoryId(parentCategoryId)
			.subCategories(subCategories)
			.build();
	}
}
