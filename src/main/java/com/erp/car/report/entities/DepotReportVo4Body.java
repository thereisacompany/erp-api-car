package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DepotReportVo4Body {
    @ApiModelProperty(required = true)
    private Long detailId;
    @ApiModelProperty(required = true)
    private String message;
}
