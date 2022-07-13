package com.jungeun.category.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryListResponse {

	private final List<CategorySelectElement> data;

}
