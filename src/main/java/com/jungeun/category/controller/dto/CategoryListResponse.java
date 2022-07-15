package com.jungeun.category.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryListResponse {

	private final Long categoryId;
	private final String title;
	private final Long parentCategoryId;
	private final List<CategorySelectElement> subCategories;

}
