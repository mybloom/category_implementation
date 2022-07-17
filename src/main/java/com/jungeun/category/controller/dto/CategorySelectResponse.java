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
	private final int categoryOrder;
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
			.categoryOrder(category.getOrder())
			.parentCategoryId(Optional.ofNullable(category.getParent())
				.orElse(Category.of(null))
				.getId())
			.build();
	}

}
