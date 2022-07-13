package com.jungeun.category.util;

import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;

public class EntityCreator {

	final static String SUPER_CATEGORY_TITLE = "상위카테고리";

	public static Category 상위카테고리_생성_요청(CategoryRepository categoryRepository) {
		return Category.makeSuper(SUPER_CATEGORY_TITLE);
	}

}
