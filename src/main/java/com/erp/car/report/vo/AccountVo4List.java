package com.erp.car.report.vo;

import com.erp.car.report.entities.Account;

public class AccountVo4List extends Account{

    private String thisMonthAmount;

    public String getThisMonthAmount() {
        return thisMonthAmount;
    }

    public void setThisMonthAmount(String thisMonthAmount) {
        this.thisMonthAmount = thisMonthAmount;
    }
}