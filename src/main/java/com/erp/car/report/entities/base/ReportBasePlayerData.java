package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportBasePlayerData {
    @ApiModelProperty("球員")
    private String player;
}
