package com.jungeun.category.controller.dto;

public interface ICategoryJoin {

	Long getSuperCategoryId();
	String getSuperTitle();
	Long getSuperParentCategoryId();

	Long getCategoryId();
	String getTitle();
	Long getParentCategoryId();

}
