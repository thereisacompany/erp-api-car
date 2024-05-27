package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AgreedDeliveryVo4Body {

    @ApiModelProperty(value = "配送單號", required = true)
    private String number;

    @ApiModelProperty(value = "約配日", required = true)
    private String datetime;



}
