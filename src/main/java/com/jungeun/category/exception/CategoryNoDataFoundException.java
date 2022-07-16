package com.jungeun.category.exception;

public class CategoryNoDataFoundException extends NoDataFoundException{

	@Override
	protected String getBodyMessage() {
		return "해당 categoryId는 존재하지 않은 카테고리입니다.";
	}
}
