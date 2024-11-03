package com.erp.car.report.entities;

import lombok.Data;

@Data
public class UserCar {
    private Long id;

    private String username;
    private String password;

    private Integer status;
    private Long supplierId;

    private Long tenantId;

    private String membernumber;

    private String licenseplate;

}