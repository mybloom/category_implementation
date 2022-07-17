package com.jungeun.category.domain;

import com.jungeun.category.controller.dto.CategoryModifySubCategoriesRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

	public static final Long PARENT_CATEGORY_ID_OF_ROOT = 0L;
	public static final int CATEGORY_DEPTH = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	private String title;

	@Column(name = "category_order")
	private int order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_category_id")
	private Category parent;

	@Builder.Default
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private Set<Category> subCategories = new HashSet<>();

	public void modify(String title) {
		this.title = title;
	}

	public void modifySubCategories(List<CategoryModifySubCategoriesRequest> subCategoriesRequest) {
		Set<Category> modifySubCategories = null;
		for (Category subCategory : subCategories) {
			modifySubCategories = subCategoriesRequest.stream()
				.filter(categoryModifyRequest -> categoryModifyRequest.getCategoryId()
					.equals(subCategory.id))
				.map(categoryModifySubCategoriesRequest -> {
					subCategory.title = categoryModifySubCategoriesRequest.getTitle();
					subCategory.order = categoryModifySubCategoriesRequest.getCategoryOrder();
					return subCategory;
				})
				.collect(Collectors.toSet());
		}

		this.subCategories = modifySubCategories;
	}

	public static Category from(
		CategoryModifySubCategoriesRequest categoryModifySubCategoriesRequest) {
		return Category.builder()
			.title(categoryModifySubCategoriesRequest.getTitle())
			.order(categoryModifySubCategoriesRequest.getCategoryOrder())
			.build();
	}

	public static Category of(Long categoryId) {
		return Category.builder()
			.id(categoryId)
			.build();
	}

	public static Category makeSuper(String title, int categoryOrder) {
		return Category.builder()
			.title(title)
			.order(categoryOrder)
			.build();
	}

	public static Category makeSub(String title, Long parentCategoryId, int categoryOrder) {
		return Category.builder()
			.title(title)
			.order(categoryOrder)
			.parent(Category.of(parentCategoryId))
			.build();
	}
}
