package com.jungeun.category.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.jungeun.category.exception.CategoryNoDataFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayName("CategoryRepository 조회 메서드 테스트")
@DataJpaTest
class CategoryRepositoryForSelectTest {

	@Autowired
	CategoryRepository categoryRepository;

	final Long SUPER_CATEGORY_ID = 1L;
	final Long SUB_CATEGORY_ID = 2L;


	@DisplayName("상위카테고리를 이용해 하위 모든 카테고리 조회")
	@Test
	void findById() {
		//when
		Category findCategory = categoryRepository.findById(SUPER_CATEGORY_ID)
			.orElseThrow(CategoryNoDataFoundException::new);

		//then
		assertAll(
			() -> assertThat(findCategory).isNotNull(),
			() -> assertThat(findCategory.getSubCategories()).hasSize(3)
		);
	}

}