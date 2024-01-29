package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GameKindBettingTime {

    @ApiModelProperty("平台")
    private String platform;
    @ApiModelProperty("會員帳號")
    private String memberId;
    @ApiModelProperty("代理線")
    private String agent;
    @ApiModelProperty("0-1")
    private BigDecimal h0;
    @ApiModelProperty("1-2")
    private BigDecimal h1;
    @ApiModelProperty("2-3")
    private BigDecimal h2;
    @ApiModelProperty("3-4")
    private BigDecimal h3;
    @ApiModelProperty("4-5")
    private BigDecimal h4;
    @ApiModelProperty("5-6")
    private BigDecimal h5;
    @ApiModelProperty("6-7")
    private BigDecimal h6;
    @ApiModelProperty("7-8")
    private BigDecimal h7;
    @ApiModelProperty("8-9")
    private BigDecimal h8;
    @ApiModelProperty("9-10")
    private BigDecimal h9;
    @ApiModelProperty("10-11")
    private BigDecimal h10;
    @ApiModelProperty("11-12")
    private BigDecimal h11;
    @ApiModelProperty("12-13")
    private BigDecimal h12;
    @ApiModelProperty("13-14")
    private BigDecimal h13;
    @ApiModelProperty("14-15")
    private BigDecimal h14;
    @ApiModelProperty("15-16")
    private BigDecimal h15;
    @ApiModelProperty("16-17")
    private BigDecimal h16;
    @ApiModelProperty("17-18")
    private BigDecimal h17;
    @ApiModelProperty("18-19")
    private BigDecimal h18;
    @ApiModelProperty("19-20")
    private BigDecimal h19;
    @ApiModelProperty("20-21")
    private BigDecimal h20;
    @ApiModelProperty("21-22")
    private BigDecimal h21;
    @ApiModelProperty("22-23")
    private BigDecimal h22;
    @ApiModelProperty("23-24")
    private BigDecimal h23;

    public BigDecimal getTotal() {
        return h0.add(h1).add(h2).add(h3).add(h4).add(h5).add(h6).add(h7).add(h8).add(h9).add(h10).
                add(h11).add(h12).add(h13).add(h14).add(h15).add(h16).add(h17).add(h18).add(h19).add(h20)
                .add(h21).add(h22).add(h23);
    }
}
