package com.jungeun.category.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CategoryIdResponse {

	private Long categoryId;

	public static CategoryIdResponse of(Long categoryId) {
		return new CategoryIdResponse(categoryId);
	}
}
