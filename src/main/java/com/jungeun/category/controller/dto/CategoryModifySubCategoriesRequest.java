package com.jungeun.category.controller.dto;

import lombok.Getter;

@Getter
public class CategoryModifySubCategoriesRequest {

	private Long categoryId;
	private String title;
	private int categoryOrder;

}
