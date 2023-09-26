package com.leo.demo.shopping.models.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @Column(columnDefinition = "integer NOT NULL", nullable = false)
    private Integer orderId;
    @Column(columnDefinition = "integer NOT NULL", nullable = false)
    private Integer mealId;
    @Column(columnDefinition = "integer NOT NULL", nullable = false)
    private Integer quantity;
    @Column(columnDefinition = "decimal(10,2) NOT NULL default 0.00", nullable = false)
    private BigDecimal unitPrice;
    @Column(columnDefinition = "decimal(10,2) NOT NULL default 0.00", nullable = false)
    private BigDecimal totalAmount;
    @CreationTimestamp
    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updateTime;
    @Column(columnDefinition = "varchar(20) default ''", nullable = false)
    private String createBy = "";
    @Column(columnDefinition = "varchar(20) default ''", nullable = false)
    private String updateBy = "";
}
