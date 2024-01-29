package com.erp.car.report.entities.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportBaseGroupMember {
    @ApiModelProperty("日期")
    private String accountdate;
    @ApiModelProperty("會員帳號")
    private String memberid;
    @ApiModelProperty("平台")
    private String platform;
    @ApiModelProperty("代理線")
    private String agentid;
}
