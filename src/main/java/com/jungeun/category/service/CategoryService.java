package com.jungeun.category.service;

import com.jungeun.category.controller.dto.CategoryListResponse;
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
}
