package com.leo.demo.shopping.dao;


import com.leo.demo.shopping.models.entities.OrderMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Repository
public interface OrderMealRepository extends JpaRepository<OrderMeal, Integer>, JpaSpecificationExecutor<OrderMeal> {
    List<OrderMeal> findByOrderIdEquals(@NonNull Integer orderId);
}
