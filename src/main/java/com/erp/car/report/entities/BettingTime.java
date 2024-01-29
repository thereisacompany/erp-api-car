package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BettingTime {
    @ApiModelProperty("下注數值")
    BigDecimal amount;
    @ApiModelProperty("區間")
    BigDecimal avg;
    @ApiModelProperty("階層")
    Integer level;
}
