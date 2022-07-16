package com.jungeun.category.service;

import static java.util.stream.Collectors.groupingBy;

import com.jungeun.category.controller.dto.CategoryIdResponse;
import com.jungeun.category.controller.dto.CategorySelectResponse;
import com.jungeun.category.controller.dto.CategoryModifyRequest;
import com.jungeun.category.controller.dto.CategorySaveRequest;
import com.jungeun.category.domain.Category;
import com.jungeun.category.domain.CategoryRepository;
import com.jungeun.category.exception.CategoryDepthInvalidException;
import com.jungeun.category.exception.CategoryNoDataFoundException;
import com.jungeun.category.exception.SuperCategoryIdInvalidException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public CategorySelectResponse selectSubByParent(Long categoryId) {
		Category category = validateSuperCategoryId(categoryId);

		CategorySelectResponse response = CategorySelectResponse.from(category);
		response.setSubCategories(category.getSubCategories().stream()
			.map(CategorySelectResponse::from)
			.collect(Collectors.toList()));

		return response;
	}

	private Category validateSuperCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(CategoryNoDataFoundException::new);

		if(category.getParent() != null){
			throw new SuperCategoryIdInvalidException();
		}
		return category;
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
	public CategorySelectResponse retrieveDetail(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow();

		CategorySelectResponse response = CategorySelectResponse.from(category);
		response.setSubCategories(category.getSubCategories().stream()
			.map(CategorySelectResponse::from)
			.collect(Collectors.toList()));

		return response;
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
		for (CategorySelectResponse category : categories) {
			List<CategorySelectResponse> subCategories = categoryViaGroupingByParent.get(
				category.getCategoryId());
			category.setSubCategories(subCategories);
		}
	}

}
