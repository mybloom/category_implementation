package com.jungeun.category.service;

import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import com.jungeun.category.exception.CategoryDepthInvalidException;
import com.jungeun.category.exception.CategoryNoDataFoundException;
import com.jungeun.category.exception.SuperCategoryIdInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryValidateUtil {

	private final CategoryRepository categoryRepository;

	Category validateCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(CategoryNoDataFoundException::new);
		return category;
	}

	Category validateCategoryIdWithoutSubCategories(Long categoryId) {
		Category category = categoryRepository.findByIdOrderByIdDesc(categoryId)
			.orElseThrow(CategoryNoDataFoundException::new);
		return category;
	}

	Category validateSuperCategoryId(Long categoryId) {
		Category category = validateCategoryId(categoryId);

		if(category.getParent() != null){
			throw new SuperCategoryIdInvalidException();
		}
		return category;
	}

	void validateCategoryDepth(CategorySaveRequest categorySaveRequest) {
		Category parentCategory = validateCategoryIdWithoutSubCategories(categorySaveRequest.getParentCategoryId());

		if (parentCategory.getParent() != null) {
			throw new CategoryDepthInvalidException();
		}
	}
}
