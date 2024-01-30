package com.erp.car.report.vo;

import lombok.Data;

@Data
public class DepotHeadDetail {
    private Long id;
    private String status;
    private Long supplierId;
    private String supplier;
    private String assignDate;
    private String assignUser;
    private String licensePlateNumber;
    private String filePath;
}
