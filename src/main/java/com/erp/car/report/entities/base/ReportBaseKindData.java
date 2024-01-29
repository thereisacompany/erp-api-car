package com.erp.car.report.entities.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportBaseKindData {

//    public void setDate(String date) {
//        this.mDate = date;
//    }

//    private String accountdate;
    private String memberid;
    private String agentid;
    private String platform;
    private BigDecimal jzbet;
    private BigDecimal dddbet;
    private BigDecimal anotherbet;
    private BigDecimal kubet;
    private BigDecimal lotterybet;
    private BigDecimal sportbet;
    private BigDecimal validbettotal;



    private BigDecimal jzpayoff;
    private BigDecimal dddpayoff;
    private BigDecimal anotherpayoff;
    private BigDecimal kupayoff;
    private BigDecimal lotterypayoff;
    private BigDecimal sportpayoff;
    private BigDecimal payofftotal;

    private BigDecimal depositAmount;
}
