package com.jungeun.category.exception;

public class SuperCategoryIdInvalidException extends InvalidException {

	@Override
	protected String getBodyMessage() {
		return "상위카테고리만 조회가능합니다.";
	}
}
