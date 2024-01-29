package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GameKindBettingTimeDTO {

    @ApiModelProperty("平台")
    private String platform;
    @ApiModelProperty("會員帳號")
    private String memberId;
    @ApiModelProperty("代理線")
    private String agent;
    @ApiModelProperty("0-1")
    private BettingTime h0 = new BettingTime();
    @ApiModelProperty("1-2")
    private BettingTime h1 = new BettingTime();
    @ApiModelProperty("2-3")
    private BettingTime h2 = new BettingTime();
    @ApiModelProperty("3-4")
    private BettingTime h3 = new BettingTime();
    @ApiModelProperty("4-5")
    private BettingTime h4 = new BettingTime();
    @ApiModelProperty("5-6")
    private BettingTime h5 = new BettingTime();
    @ApiModelProperty("6-7")
    private BettingTime h6 = new BettingTime();
    @ApiModelProperty("7-8")
    private BettingTime h7 = new BettingTime();
    @ApiModelProperty("8-9")
    private BettingTime h8 = new BettingTime();
    @ApiModelProperty("9-10")
    private BettingTime h9 = new BettingTime();
    @ApiModelProperty("10-11")
    private BettingTime h10 = new BettingTime();
    @ApiModelProperty("11-12")
    private BettingTime h11 = new BettingTime();
    @ApiModelProperty("12-13")
    private BettingTime h12 = new BettingTime();
    @ApiModelProperty("13-14")
    private BettingTime h13 = new BettingTime();
    @ApiModelProperty("14-15")
    private BettingTime h14 = new BettingTime();
    @ApiModelProperty("15-16")
    private BettingTime h15 = new BettingTime();
    @ApiModelProperty("16-17")
    private BettingTime h16 = new BettingTime();
    @ApiModelProperty("17-18")
    private BettingTime h17 = new BettingTime();
    @ApiModelProperty("18-19")
    private BettingTime h18 = new BettingTime();
    @ApiModelProperty("19-20")
    private BettingTime h19 = new BettingTime();
    @ApiModelProperty("20-21")
    private BettingTime h20 = new BettingTime();
    @ApiModelProperty("21-22")
    private BettingTime h21 = new BettingTime();
    @ApiModelProperty("22-23")
    private BettingTime h22 = new BettingTime();
    @ApiModelProperty("23-24")
    private BettingTime h23 = new BettingTime();

}
