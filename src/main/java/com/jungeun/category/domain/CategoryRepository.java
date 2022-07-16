package com.jungeun.category.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@EntityGraph(attributePaths = {"subCategories"})
	@Override
	Optional<Category> findById(Long categoryId);

	Optional<Category> findByIdOrderByIdDesc(Long categoryId);
}
