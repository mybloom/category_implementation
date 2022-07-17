package com.jungeun.category.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CategoryModifyRequest {

	private Long categoryId;
	private String title;
	private List<CategoryModifySubCategoriesRequest> subCategories;

}
