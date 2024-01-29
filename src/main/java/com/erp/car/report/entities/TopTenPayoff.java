package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopTenPayoff {

    @ApiModelProperty("會員")
    private String name;
    @ApiModelProperty("流水")
    private BigDecimal validBet;
    @ApiModelProperty("負盈利")
    private BigDecimal payoff;

}
