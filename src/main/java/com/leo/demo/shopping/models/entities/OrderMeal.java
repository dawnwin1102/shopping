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
@Table(name = "tb_order_meal")
public class OrderMeal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer mealId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
}
