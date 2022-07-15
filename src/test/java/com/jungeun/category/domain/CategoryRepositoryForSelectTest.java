package com.jungeun.category.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.jungeun.category.controller.dto.ICategoryJoin;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryForSelectTest {

	@Autowired
	CategoryRepository categoryRepository;

	final Long SUPER_CATEGORY_ID = 1L;

	@DisplayName("상위카테고리를 이용해 하위 모든 카테고리 조회")
	@Test
	void findById() {
		//given
		Category category = Category.of(SUPER_CATEGORY_ID);

		//when
		List<ICategoryJoin> findCategories = categoryRepository.findByParentCategoryId(SUPER_CATEGORY_ID);

		//then
		assertAll(
			() -> assertThat(findCategories).isNotNull(),
			() -> assertThat(findCategories).hasSize(3)
		);
	}

}