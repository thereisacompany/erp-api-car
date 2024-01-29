package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportBaseTeamData {
    @ApiModelProperty("隊伍")
    private String teamname;
}
