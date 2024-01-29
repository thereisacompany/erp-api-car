package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardDTO {

    @ApiModelProperty("本期資料")
    private DashboardData thisPeriod;

    @Data
    private class DashboardData {
        @ApiModelProperty("會員存款統計")
        private BigDecimal memberDeposit;
        @ApiModelProperty("會員流水統計")
        private BigDecimal validBet;
        @ApiModelProperty("會員負盈利統計")
        private BigDecimal payoff;
        @ApiModelProperty("總成長")
        private BigDecimal growing;
    }
}
