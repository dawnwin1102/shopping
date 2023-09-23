package com.leo.demo.shopping.models.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author leo
 * @date 2023/9/24
 */
@Entity
@Data
public class MealItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "UnitPrice")
    private BigDecimal unitPrice;
    @CsvBindByName(column = "Inventory")
    private Integer inventory;
    @CsvBindByName(column = "Description")
    private String description;
    @CsvBindByName(column = "ImgUrl")
    private String imgUrl;
}
