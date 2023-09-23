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
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer orderNumber;
    //"waitPay","paid","canceled","refund","delivered"
    private String orderStatus;
    private BigDecimal totalAmount;
    private String contactMobile;
    private String userName;
    private String address;
    private String email;
    private String transactionNo;
    private LocalDateTime payTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
}