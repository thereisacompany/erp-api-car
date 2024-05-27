package com.erp.car.report.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class DepotHeadDelivery {

    private String number;
    private String customNumber;
    private String sourceNumber;
    private String customName;
    private String orderDate;
    private String takeDate;
    private List<JSONObject> agreedDelivery;
    private Long driverId;
    private String driverName;
    private String carNumber;
    private Long assignUser;
    private String memo;
    private String filePath;
    private String status;
    private List<DeliveryStatus> deliveryStatusList;


}
