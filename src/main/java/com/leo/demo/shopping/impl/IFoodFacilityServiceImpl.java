package com.leo.demo.shopping.impl;

import com.leo.demo.shopping.cache.FoodFacilityCache;
import com.leo.demo.shopping.dao.FoodFacilityRepository;
import com.leo.demo.shopping.models.dto.food.FoodFacilityPageRequest;
import com.leo.demo.shopping.models.dto.food.FoodFacilityRequest;
import com.leo.demo.shopping.models.entities.FoodFacility;
import com.leo.demo.shopping.service.IFoodFacilityService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IFoodFacilityServiceImpl implements IFoodFacilityService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FoodFacilityRepository foodFacilityRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private FoodFacilityCache foodFacilityCache;

    @Override
    public List<FoodFacility> initFoodFacilityDB(String fileName) throws IOException, CsvException {
        if (StringUtils.isBlank(fileName) || Files.notExists(Paths.get(fileName))) {
            // set to default file
            fileName = "datafile/Mobile_Food_Facility_Permit.csv";
        }
        ClassPathResource CPR = new ClassPathResource(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(CPR.getInputStream());
        List<FoodFacility> beans = new CsvToBeanBuilder(inputStreamReader)
                .withType(FoodFacility.class).build().parse();
        return foodFacilityRepository.saveAllAndFlush(beans);
    }

    @Override
    public Page<FoodFacility> getFoodFacilitylist(FoodFacilityPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return foodFacilityRepository.findAll(pageable);
    }

    @Override
    public Long getFoodFacilityCount() {
        return foodFacilityRepository.count();
    }

    @Override
    public FoodFacility getFoodFacilityDetail(Integer id) {
        FoodFacility res = foodFacilityRepository.findById(id).get();
        return res;
    }

    @Override
    public Optional<FoodFacility> getFoodFacilityByLocationId(Integer locationId) {
        return foodFacilityRepository.findByLocationId(locationId);
    }

    @Override
    public List<FoodFacility> getFoodFacilityByApplicant(FoodFacilityRequest request) {
        // we can do something to create specific response, here just return simple cached value
        return foodFacilityCache.getAllFoodFacilityByApplicant(request);
    }

    @Override
    public List<FoodFacility> getFoodFacilityByApplicantOrAddress(FoodFacilityRequest request) {
        Specification<FoodFacility> specification = new Specification<FoodFacility>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<FoodFacility> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String applicant = request.getApplicant();
                String address = request.getAddress();
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(applicant)) {
                    Predicate predicate = cb.like(root.get("applicant").as(String.class), "%" + applicant + "%");
                    predicates.add(predicate);
                }
                if (StringUtils.isNotBlank(address)) {
                    Predicate predicate = cb.like(root.get("address").as(String.class), "%" + address + "%");
                    predicates.add(predicate);
                }
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] p = new Predicate[predicates.size()];
                return cb.and(predicates.toArray(p));
            }
        };

        return foodFacilityRepository.findAll(specification);
    }
}
