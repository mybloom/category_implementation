package com.jungeun.category.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CategoryModifyRequest {

	//pathVariable 에 있으므로 없어도 된다.
	private Long categoryId;
	private String title;
	private List<CategoryModifySubCategoriesRequest> subCategories;

}
