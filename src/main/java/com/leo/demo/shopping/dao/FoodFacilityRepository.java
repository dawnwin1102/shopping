package com.leo.demo.shopping.dao;


import com.leo.demo.shopping.models.entities.FoodFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author leo
 * @date 2023/9/24
 */
@Repository
public interface FoodFacilityRepository extends JpaRepository<FoodFacility, Integer>, JpaSpecificationExecutor<FoodFacility> {
    Optional<FoodFacility> findByLocationId(Integer locationId);

    List<FoodFacility> findAllByApplicant(String applicant);

}
