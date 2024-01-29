package com.erp.car.report.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopGameDTO {

    @ApiModelProperty("平台")
    private String platform;
    @ApiModelProperty("會員帳號")
    private String memberId;
    @ApiModelProperty("代理線")
    private String agent;
    @ApiModelProperty("第一大遊戲")
    private GameDetail firstGame = new GameDetail();
    @ApiModelProperty("第二大遊戲")
    private GameDetail secondGame = new GameDetail();
    @ApiModelProperty("第三大遊戲")
    private GameDetail thirdGame = new GameDetail();

}
