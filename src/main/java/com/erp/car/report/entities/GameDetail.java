package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GameDetail {
    @ApiModelProperty("遊戲")
    private String game;
    @ApiModelProperty("彩種")
    private String gamekind;
    @ApiModelProperty("投注金額")
    private BigDecimal bettingGold = BigDecimal.ZERO;
    @ApiModelProperty("比例")
    private BigDecimal ratio = BigDecimal.ZERO;
}
