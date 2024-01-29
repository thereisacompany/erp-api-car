package com.erp.car.report.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopGame {
    private String platform;
    private String memberId;
    private String agent;
    private String gamename;
    private String gamekind;
    private BigDecimal validBet;
}
