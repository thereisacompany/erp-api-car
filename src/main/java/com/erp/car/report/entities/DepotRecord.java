package com.erp.car.report.entities;

import lombok.Data;

@Data
public class DepotRecord {
    private Long id;
    private Long detailId;
    private String status;
    private String date;
}
