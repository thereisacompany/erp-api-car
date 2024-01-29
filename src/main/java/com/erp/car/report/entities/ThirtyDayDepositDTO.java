package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ThirtyDayDepositDTO {

    @ApiModelProperty("近30日加總數值")
    private TotalData total;

    @ApiModelProperty("每日數值")
    private List<ThirtyDayDeposit> rows;

    @Data
    private class TotalData {
        @ApiModelProperty("會員存款")
        private BigDecimal memberDeposit;
        @ApiModelProperty("流水")
        private BigDecimal validBet;
        @ApiModelProperty("負盈利")
        private BigDecimal payoff;
    }
}
