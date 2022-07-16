package com.jungeun.category.controller;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategorySelectResponse;
import com.jungeun.category.controller.dto.CategoryModifyRequest;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@GetMapping("/category")
	public ResponseEntity<List<CategorySelectResponse>> retrieveAllHierarchy() {
		List<CategorySelectResponse> response = categoryService.retrieveAllHierarchy();

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategorySelectResponse> retrieveSubByParent(
		@PathVariable final Long categoryId) {
		CategorySelectResponse response = categoryService.selectSubByParent(categoryId);

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
	public ResponseEntity<CategorySelectResponse> retrieveDetail(@PathVariable Long categoryId) {
		CategorySelectResponse response = categoryService.retrieveDetail(categoryId);
		return ResponseEntity.ok().body(response);
	}

	@PatchMapping("/category/{categoryId}")
	public ResponseEntity<CategoryIdResponse> modify(
		@PathVariable Long categoryId,
		@RequestBody CategoryModifyRequest categoryModifyRequest) {

		CategoryIdResponse response = categoryService.modify(categoryId, categoryModifyRequest);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<CategoryIdResponse> delete(@PathVariable Long categoryId) {
		CategoryIdResponse response = categoryService.delete(categoryId);

		return ResponseEntity.ok().body(response);
	}
}
