package com.jungeun.category.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.jungeun.category.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayName("Category Repository For Command Test")
@DataJpaTest
public class CategoryRepositoryForCommandTest {

	@Autowired
	CategoryRepository categoryRepository;

	@DisplayName("카테고리 등록 테스트")
	@Test
	void save() {
		//given
		Category category = EntityCreator.상위카테고리_생성_요청(categoryRepository);

		//when
		Category savedCategory = categoryRepository.save(category);

		//then
		assertAll(
			() -> assertThat(savedCategory.getId()).isNotNull(),
			() -> assertThat(savedCategory.getOrder()).isEqualTo(3),
			() -> assertThat(savedCategory.getTitle()).isEqualTo(category.getTitle())
		);
	}
}
