package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportBaseDayDetail {
    @ApiModelProperty("總流水")
    private BigDecimal validBet;
    @ApiModelProperty("總損益")
    private BigDecimal payoff;
    @ApiModelProperty("日期")
    private String accountdate;
    @ApiModelProperty("總金額")
    private BigDecimal depositAmount;


}
