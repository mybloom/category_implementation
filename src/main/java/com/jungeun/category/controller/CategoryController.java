package com.jungeun.category.controller;

import com.jungeun.category.controller.dto.CategoryListResponse;
import com.jungeun.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategoryListResponse> selectSubByParent(@PathVariable final Long categoryId) {
		CategoryListResponse response = categoryService.selectSubByParent(categoryId);

		return ResponseEntity.ok().body(response);
	}
}
