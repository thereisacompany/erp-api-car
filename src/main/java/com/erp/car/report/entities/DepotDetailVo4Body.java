package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DepotDetailVo4Body {
    @ApiModelProperty(required = true)
    private Long headerId;
    @ApiModelProperty(required = true)
    private Integer orderStatus;
}
