package com.leo.demo.shopping.models.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author leo
 * @date 2023/1/17
 */
@Entity
@Data
public class FoodFacility implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CsvBindByName(column = "locationid")
    private Integer locationId;
    @CsvBindByName(column = "Applicant")
    private String applicant;
    @CsvBindByName(column = "FacilityType")
    private String facilityType;
    @CsvBindByName(column = "cnn")
    private Integer cnn;
    @CsvBindByName(column = "LocationDescription")
    private String locationDescription;
    @CsvBindByName(column = "Address")
    private String address;
    @CsvBindByName(column = "blocklot")
    private String blockLot;
    @CsvBindByName(column = "block")
    private String block;
    @CsvBindByName(column = "lot")
    private String lot;
    @CsvBindByName(column = "permit")
    private String permit;
    @CsvBindByName(column = "Status")
    private String status;
    @CsvBindByName(column = "FoodItems")
    private String foodItems;
    @CsvBindByName(column = "x")
    @Column(nullable = true)
    private Double x;
    @CsvBindByName(column = "y")
    @Column(nullable = true)
    private Double y;
    @CsvBindByName(column = "latitude")
    private Double latitude;
    @CsvBindByName(column = "longitude")
    private Double longitude;
    @CsvBindByName(column = "Schedule")
    private String schedule;
    @CsvBindByName(column = "dayshours")
    private String daysHours;
    @CsvBindByName(column = "NOISent")
    private String noiSent;
    @CsvBindByName(column = "Approved")
    @CsvDate(value = "MM/dd/yyyy hh:mm:ss a")
    private LocalDateTime approved;
    @CsvBindByName(column = "Received")
    @CsvDate(value = "yyyyMMdd")
    private LocalDate received;
    @CsvBindByName(column = "PriorPermit")
    private Integer priorPermit;
    @CsvBindByName(column = "ExpirationDate")
    @CsvDate(value = "MM/dd/yyyy hh:mm:ss a")
    private LocalDateTime expirationDate;
    /**
     * location is (latitude,longitude)
     */
    @CsvBindByName(column = "Location")
    private String location;
    @CsvBindByName(column = "Fire Prevention Districts")
    private Integer firePreventionDistricts;
    @CsvBindByName(column = "Police Districts")
    private Integer policeDistricts;
    @CsvBindByName(column = "Supervisor Districts")
    private Integer supervisorDistricts;
    @CsvBindByName(column = "Zip Codes")
    private Integer zipCodes;
    @CsvBindByName(column = "Neighborhoods (old)")
    private Integer oldNeighborhoods;
}
