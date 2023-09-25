package com.leo.demo.shopping.dao;


import com.leo.demo.shopping.models.entities.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author leo
 * @date 2023/9/24
 */
@Repository
public interface MealItemRepository extends JpaRepository<MealItem, Integer>, JpaSpecificationExecutor<MealItem> {
}
