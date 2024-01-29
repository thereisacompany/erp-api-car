package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportBaseManagerAreaData {
    @ApiModelProperty("單位")
    private String managerarea;
}
