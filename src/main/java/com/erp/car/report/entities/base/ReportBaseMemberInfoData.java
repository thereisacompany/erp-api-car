package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportBaseMemberInfoData {
    @ApiModelProperty("會員帳號")
    private String memberid;
    @ApiModelProperty("平台")
    private String platform;
    @ApiModelProperty("代理線")
    private String agent;


    @ApiModelProperty("註冊日期")
    private String registrationdate;
    @ApiModelProperty("起飛日期")
    private String flightdate;
    @ApiModelProperty("未登入(天)")
    private String nonelogindays;
    @ApiModelProperty("未投注(天)")
    private String nobettingdays;
    @ApiModelProperty("會員等級")
    private String level;
    @ApiModelProperty("用戶狀態")
    private String memberstatus;
    @ApiModelProperty("儲值次數")
    private String depositcount;
    @ApiModelProperty("總儲值")
    private BigDecimal totaldeposit;
    @ApiModelProperty("總輸贏")
    private BigDecimal totalpayoff;
//    private BigDecimal depositAmount;
}
