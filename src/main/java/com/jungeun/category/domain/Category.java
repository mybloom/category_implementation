package com.jungeun.category.domain;

import java.util.ArrayList;
import java.util.List;
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

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<Category> subCategories = new ArrayList<>();

	public void modify(String title) {
		this.title = title;
	}

	public static Category of(Long categoryId) {
		return Category.builder()
			.id(categoryId)
			.build();
	}

	public static Category makeSuper(String title) {
		return Category.builder()
			.title(title)
			.build();
	}

	public static Category makeSub(String title, Long parentCategoryId) {
		return Category.builder()
			.title(title)
			.parent(Category.of(parentCategoryId))
			.build();
	}
}
