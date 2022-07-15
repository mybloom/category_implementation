package com.jungeun.category.domain;

import com.jungeun.category.controller.dto.ICategoryJoin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(
		"select distinct "
			+ "p.id as superCategoryId, "
			+ "p.title as superTitle, "
			+ "p.parent.id as superParentCategoryId, "
			+ "s.id as categoryId, "
			+ "s.title as title, "
			+ "s.parent.id as parentCategoryId "
			+ "from Category p "
			+ "left join Category s "
			+ "on p.id = s.parent.id "
			+ "where s.parent.id = :parentCategoryId ")
	List<ICategoryJoin> findByParentCategoryId(@Param("parentCategoryId") Long parentCategoryId);

}
