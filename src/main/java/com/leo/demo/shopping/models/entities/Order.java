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
@Table(name = "tb_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String orderNumber;
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String orderStatus;
    @Column(columnDefinition = "decimal(10,2) NOT NULL default 0.00", nullable = false)
    private BigDecimal totalAmount;
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String contactMobile;
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String userName = "";
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String address = "";
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String email = "";
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String transactionNo = "";
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String cardNo = "";
    @Column(columnDefinition = "varchar(50) default ''", nullable = false)
    private String CVC = "";
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime expiryDate;
    @Column(columnDefinition = "datetime", nullable = true)
    private LocalDateTime payTime;
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
