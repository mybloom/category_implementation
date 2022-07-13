package com.jungeun.category.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

	final Long CATEGORY_ID = 1L;

	@DisplayName("상위카테고리를 이용해 하위 모든 카테고리 조회")
	@Test
	void findById() {
		//given
		Category category = Category.of(CATEGORY_ID);

		//when
		List<Category> findCategories = categoryRepository.findByParent(category);

		//then
		assertAll(
			() -> assertThat(findCategories).isNotNull(),
			() -> assertThat(findCategories).hasSize(3)
		);

	}

}