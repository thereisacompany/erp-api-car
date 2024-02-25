package com.erp.car.report.entities;

import lombok.Data;

@Data
public class DepotDetail {
    private Long id;
    private Long headerId;
    private Long itemId;
    private String status;
    private Integer driverId;
    private String assignDate;
    private String assignUser;
    private String filePath;
}
