package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DepotDeliveryVo4Body {
    @ApiModelProperty(value = "配送單號", required = true)
    private String number;
    @ApiModelProperty(value = "司機id", required = true)
    private Integer driverId;
}
