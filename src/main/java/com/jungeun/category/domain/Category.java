package com.jungeun.category.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_category_id")
	private Category parent;

	public void update(String title, Long parentCategoryId) {
		this.title = title;
		parent = Category.of(parentCategoryId);
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
