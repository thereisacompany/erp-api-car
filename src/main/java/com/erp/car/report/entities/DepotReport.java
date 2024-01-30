package com.erp.car.report.entities;

import lombok.Data;

@Data
public class DepotReport {
    private Long id;
//    private Long detailId;
    private String datetime;
    private String message;
    private String feedback;
}
