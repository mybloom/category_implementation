package com.jungeun.category.controller;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategoryListResponse;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.controller.dto.CategorySelectElement;
import com.jungeun.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategoryListResponse> retrieveSubByParent(
		@PathVariable final Long categoryId) {
		CategoryListResponse response = categoryService.selectSubByParent(categoryId);

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/category")
	public ResponseEntity<CategoryIdResponse> create(
		@RequestBody CategorySaveRequest categorySaveRequest) {

		CategoryIdResponse response = null;
		if (categorySaveRequest.getParentCategoryId() == null) {
			response = categoryService.createSuper(categorySaveRequest);
		} else {
			response = categoryService.createSub(categorySaveRequest);
		}

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/category/{categoryId}/detail")
	public ResponseEntity<CategorySelectElement> retrieveDetail(@PathVariable Long categoryId) {
		CategorySelectElement response = categoryService.retrieveDetail(categoryId);
		return ResponseEntity.ok().body(response);
	}



}
