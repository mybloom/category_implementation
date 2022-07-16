package com.jungeun.category.service;

import static java.util.stream.Collectors.groupingBy;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategoryListAllResponse;
import com.jungeun.category.controller.dto.CategoryListResponse;
import com.jungeun.category.controller.dto.CategoryModifyRequest;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.controller.dto.CategorySelectElement;
import com.jungeun.category.controller.dto.ICategoryJoin;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import com.jungeun.category.exception.CategoryDepthInvalidException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public CategoryListResponse selectSubByParent(Long categoryId) {
		List<ICategoryJoin> categories = categoryRepository.findByParentCategoryId(categoryId);

		List<CategorySelectElement> response = new ArrayList<>();
		for (ICategoryJoin category : categories) {
			response.add(CategorySelectElement.from(category));
		}

		return new CategoryListResponse(
			categories.get(0).getSuperCategoryId(),
			categories.get(0).getSuperTitle(),
			categories.get(0).getSuperParentCategoryId(),
			response);
	}

	@Transactional
	public CategoryIdResponse createSuper(CategorySaveRequest categorySaveRequest) {
		Category category = categoryRepository.save(
			Category.makeSuper(categorySaveRequest.getTitle()));
		return CategoryIdResponse.of(category.getId());
	}

	@Transactional
	public CategoryIdResponse createSub(CategorySaveRequest categorySaveRequest) {

		validateCategoryDepth(categorySaveRequest);

		Category category = categoryRepository.save(Category.makeSub(categorySaveRequest.getTitle(),
			categorySaveRequest.getParentCategoryId()));
		return CategoryIdResponse.of(category.getId());
	}

	private void validateCategoryDepth(CategorySaveRequest categorySaveRequest) {
		Category parentCategory = categoryRepository.findById(
				categorySaveRequest.getParentCategoryId())
			.orElseThrow();
		if (parentCategory.getParent() != null) {
			throw new CategoryDepthInvalidException();
		}
	}

	@Transactional(readOnly = true)
	public CategorySelectElement retrieveDetail(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow();

		return CategorySelectElement.from(category);
	}

	@Transactional
	public CategoryIdResponse modify(Long categoryId,
		CategoryModifyRequest categoryModifyRequest) {

		Category category = categoryRepository.findById(categoryId)
			.orElseThrow();

		category.modify(categoryModifyRequest.getTitle());

		return CategoryIdResponse.of(categoryId);
	}

	@Transactional
	public CategoryIdResponse delete(Long categoryId) {
		categoryRepository.deleteById(categoryId);

		return CategoryIdResponse.of(categoryId);
	}

	@Transactional(readOnly = true)
	public List<CategoryListAllResponse> retrieveAllHierarchy() {
		List<Category> categories = categoryRepository.findAll();

		Map<Long, List<CategoryListAllResponse>> categoryViaGroupingByParent
			= categories.stream()
			.map(CategoryListAllResponse::from)
			.collect(groupingBy(
				categoryListAllResponse -> Optional.ofNullable(
						categoryListAllResponse.getParentCategoryId())
					.orElse(0L)));

		addSubCategories(categoryViaGroupingByParent);

		return categoryViaGroupingByParent.get(Category.PARENT_CATEGORY_ID_OF_ROOT);
	}

	private void addSubCategories(
		Map<Long, List<CategoryListAllResponse>> categoryViaGroupingByParent) {

		List<CategoryListAllResponse> categories = categoryViaGroupingByParent.get(
			Category.PARENT_CATEGORY_ID_OF_ROOT);
		for (CategoryListAllResponse category : categories) {
			List<CategoryListAllResponse> subCategories = categoryViaGroupingByParent.get(
				category.getCategoryId());
			category.setSubCategories(subCategories);
		}
	}

}
