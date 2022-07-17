package com.jungeun.category.service;

import static java.util.stream.Collectors.groupingBy;

import com.jungeun.category.controller.dto.CategorySelectResponse;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryQueryService {

	private final CategoryRepository categoryRepository;
	private final CategoryValidateUtil categoryValidateUtil;

	@Transactional(readOnly = true)
	public CategorySelectResponse retrieveSubByParent(Long categoryId) {
		Category category = categoryValidateUtil.validateSuperCategoryId(categoryId);

		CategorySelectResponse response = CategorySelectResponse.from(category);

		List<CategorySelectResponse> categorySelectResponses = category.getSubCategories().stream()
			.map(CategorySelectResponse::from)
			.collect(Collectors.toList());
		categorySelectResponses.sort(
			Comparator.comparing(CategorySelectResponse::getCategoryOrder));

		response.setSubCategories(categorySelectResponses);

		return response;
	}

	@Transactional(readOnly = true)
	public CategorySelectResponse retrieveDetail(Long categoryId) {
		Category category = categoryValidateUtil.validateCategoryId(categoryId);

		CategorySelectResponse response = CategorySelectResponse.from(category);
		List<CategorySelectResponse> subCategories = category.getSubCategories().stream()
			.map(CategorySelectResponse::from)
			.collect(Collectors.toList());
		subCategories.sort(Comparator.comparing(CategorySelectResponse::getCategoryOrder));

		response.setSubCategories(subCategories);
		return response;
	}

	@Transactional(readOnly = true)
	public List<CategorySelectResponse> retrieveAllHierarchy() {
		List<Category> categories = categoryRepository.findAll();

		Map<Long, List<CategorySelectResponse>> categoryViaGroupingByParent
			= categories.stream()
			.map(CategorySelectResponse::from)
			.collect(groupingBy(
				categoryListAllResponse -> Optional.ofNullable(
						categoryListAllResponse.getParentCategoryId())
					.orElse(0L)));

		addSubCategories(categoryViaGroupingByParent);

		return categoryViaGroupingByParent.get(Category.PARENT_CATEGORY_ID_OF_ROOT);
	}

	private void addSubCategories(
		Map<Long, List<CategorySelectResponse>> categoryViaGroupingByParent) {

		List<CategorySelectResponse> categories = categoryViaGroupingByParent.get(
			Category.PARENT_CATEGORY_ID_OF_ROOT);
		categories.sort(Comparator.comparing(CategorySelectResponse::getCategoryOrder));

		for (CategorySelectResponse category : categories) {
			List<CategorySelectResponse> subCategories = categoryViaGroupingByParent.get(
				category.getCategoryId());

			if (subCategories != null) {
				subCategories.sort(Comparator.comparing(CategorySelectResponse::getCategoryOrder));
			}

			category.setSubCategories(subCategories);
		}
	}

}
