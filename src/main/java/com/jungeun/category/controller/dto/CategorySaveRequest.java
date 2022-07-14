package com.jungeun.category.controller.dto;

import lombok.Getter;

@Getter
public class CategorySaveRequest {

	private String title;
	private Long parentCategoryId;

}
