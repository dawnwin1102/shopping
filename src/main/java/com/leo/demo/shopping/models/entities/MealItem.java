package com.leo.demo.shopping.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author leo
 * @date 2023/9/24
 */
@Entity
@Data
@Table(name = "tb_meal_item")
public class MealItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CsvBindByName(column = "Name")
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String name = "";
    @CsvBindByName(column = "UnitPrice")
    @Column(columnDefinition = "integer default 0.00", nullable = false)
    private BigDecimal unitPrice;
    @CsvBindByName(column = "Inventory")
    @Column(columnDefinition = "integer default 99999", nullable = false)
    private Integer inventory = 99999;
    @CsvBindByName(column = "Description")
    @Column(columnDefinition = "varchar(100) default ''", nullable = false)
    private String description = "";
    @CsvBindByName(column = "ImgUrl")
    @Column(columnDefinition = "varchar(100) default ''", nullable = false)
    private String imgUrl = "";
    @CreationTimestamp
    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime createTime;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updateTime;
    @Column(columnDefinition = "varchar(20) default ''", nullable = false)
    private String createBy = "";
    @Column(columnDefinition = "varchar(20) default ''", nullable = false)
    private String updateBy = "";
}
