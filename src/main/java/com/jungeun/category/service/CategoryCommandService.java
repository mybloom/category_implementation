package com.jungeun.category.service;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategoryModifyRequest;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import com.jungeun.category.exception.CategoryNoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryCommandService {

	private final CategoryRepository categoryRepository;
	private final CategoryValidateUtil categoryValidateUtil;

	@Transactional
	public CategoryIdResponse createSuper(CategorySaveRequest categorySaveRequest) {
		Category maxOrderCategory = categoryRepository.findFirstByParentIsNullOrderByOrderDesc();

		Category category = categoryRepository.save(
			Category.makeSuper(categorySaveRequest.getTitle(),
				maxOrderCategory.getOrder() + 1));

		return CategoryIdResponse.of(category.getId());
	}

	@Transactional
	public CategoryIdResponse createSub(CategorySaveRequest categorySaveRequest) {

		categoryValidateUtil.validateCategoryDepth(categorySaveRequest);

		Category category = categoryRepository.save(Category.makeSub(categorySaveRequest.getTitle(),
			categorySaveRequest.getParentCategoryId()));
		return CategoryIdResponse.of(category.getId());
	}

	@Transactional
	public CategoryIdResponse modify(Long categoryId,
		CategoryModifyRequest categoryModifyRequest) {

		Category category = categoryValidateUtil.validateCategoryIdWithoutSubCategories(categoryId);

		category.modify(categoryModifyRequest.getTitle());

		return CategoryIdResponse.of(categoryId);
	}

	@Transactional
	public CategoryIdResponse delete(Long categoryId) {
		try {
			categoryRepository.deleteById(categoryId);
		} catch (EmptyResultDataAccessException e) {
			throw new CategoryNoDataFoundException();
		}
		return CategoryIdResponse.of(categoryId);
	}

}
