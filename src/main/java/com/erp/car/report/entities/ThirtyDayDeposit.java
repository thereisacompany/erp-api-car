package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ThirtyDayDeposit {

    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("會員存款")
    private BigDecimal memberDeposit;
    @ApiModelProperty("流水")
    private BigDecimal validBet;
    @ApiModelProperty("負盈利")
    private BigDecimal payoff;

    public void sum(ThirtyDayDeposit data) {
        if(this.memberDeposit == null) {
            this.memberDeposit = new BigDecimal(0);
        }
        if (data.memberDeposit != null) {
            this.memberDeposit = memberDeposit.add(data.getMemberDeposit());
        }
        if(this.validBet == null) {
            this.validBet = new BigDecimal(0);
        }
        if(data.validBet != null) {
            this.validBet = validBet.add(data.getValidBet());
        }
        if(this.payoff == null) {
            this.payoff = new BigDecimal(0);
        }
        if(data.payoff != null) {
            this.payoff = payoff.add(data.payoff);
        }
    }
}
