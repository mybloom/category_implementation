package com.jungeun.category.service;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategoryListResponse;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.controller.dto.CategorySelectElement;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public CategoryListResponse selectSubByParent(Long categoryId) {
		List<Category> subCategories = categoryRepository.findByParent(Category.of(categoryId));

		return new CategoryListResponse(subCategories.stream()
			.map(CategorySelectElement::from)
			.collect(Collectors.toList()));
	}

	@Transactional
	public CategoryIdResponse createSuper(CategorySaveRequest categorySaveRequest) {
		Category category = categoryRepository.save(
			Category.makeSuper(categorySaveRequest.getTitle()));
		return CategoryIdResponse.of(category.getId());
	}

	@Transactional
	public CategoryIdResponse createSub(CategorySaveRequest categorySaveRequest) {
		Category category = categoryRepository.save(Category.makeSub(categorySaveRequest.getTitle(),
			categorySaveRequest.getParentCategoryId()));
		return CategoryIdResponse.of(category.getId());
	}

	public CategorySelectElement retrieveDetail(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow();

		return CategorySelectElement.from(category);
	}

	@Transactional
	public CategoryIdResponse modify(Long categoryId,
		CategorySaveRequest categorySaveRequest) {

		Category category = categoryRepository.findById(categoryId)
			.orElseThrow();

		category.update(categorySaveRequest.getTitle(), categorySaveRequest.getParentCategoryId());

		return CategoryIdResponse.of(categoryId);
	}
}
