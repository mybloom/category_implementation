package com.jungeun.category.exception;

import com.jungeun.category.domain.Category;

public class CategoryDepthInvalidException extends InvalidException {

	@Override
	protected String getBodyMessage() {
		return "유효한 카테고리 depth가 아닙니다. 유효한 카테고리 depth는 " + Category.CATEGORY_DEPTH + "입니다.";
	}
}
