package com.erp.car.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erp.car.constants.BusinessConstants;
import com.erp.car.report.entities.DepotHead;
import com.erp.car.report.entities.DepotItemVo4WithInfoEx;
import com.erp.car.report.entities.Unit;
import com.erp.car.report.vo.DepotHeadDelivery;
import com.erp.car.report.vo.DepotHeadVo4List;
import com.erp.car.service.depotHead.DepotHeadComponent;
import com.erp.car.service.depotHead.DepotHeadService;
import com.erp.car.service.depotItem.DepotItemService;
import com.erp.car.service.material.MaterialService;
import com.erp.car.service.unit.UnitService;
import com.erp.car.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/frontend")
@Api(tags = {"司機前端管理"})
public class FrontEndController {

    @Resource
    private DepotHeadService depotHeadService;

    @Resource
    private DepotItemService depotItemService;


    @Resource
    private MaterialService materialService;


    @Resource
    private UnitService unitService;

    @Resource
    private DepotHeadComponent depotHeadComponent;


    @GetMapping(value = "/depotHead/list")
    @ApiOperation(value = "派發列表")
    public BaseResponseInfo getDepotList(
    @RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
    @RequestParam(value = Constants.SEARCH, required = false) String search,
    HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
        Map<String, String> parameterMap = ParamUtils.requestToMap(request);
        parameterMap.put(Constants.SEARCH, search);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }



        List<?> list = depotHeadComponent.select(parameterMap);
            res.code = 200;
            res.data = list;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    @GetMapping(value = "/getDeliveryData")
    @ApiOperation(value = "取得配送單狀態")
    public BaseResponseInfo getDeliveryData(@RequestParam("number") String number, HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        DepotHeadDelivery dhd = new DepotHeadDelivery();
        try {
            String[] numbers = new String[] {number};

            List<DepotHeadVo4List> list = depotHeadService.getDetailCarByNumber(numbers);
            if(list.size() >= 1) {
                DepotHeadVo4List dhl = list.get(0);
                if(dhl.getType().equals(BusinessConstants.DEPOTHEAD_TYPE_OUT)) {
                    dhd = depotHeadService.getDeliveryDetail(dhl);
                }
            }

            res.code = 200;
            res.data = dhd;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "獲取資料失敗";
        }
        return res;
    }


    /**
     * 根据编号查询单据信息
     * @param number
     * @param request
     * @return
     */
    @GetMapping(value = "/getDetailByNumber")
    @ApiOperation(value = "訂單資訊Detail")
    public BaseResponseInfo getDetailByNumber(@RequestParam("number") String number,
                                              HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        DepotHeadVo4List dhl = new DepotHeadVo4List();
        try {
            String[] numbers = new String[] {number};
            List<DepotHeadVo4List> list = depotHeadService.getDetailByNumber(numbers);
            if(list.size() >= 1) {
                dhl = list.get(0);
                if(dhl.getType().equals(BusinessConstants.DEPOTHEAD_TYPE_OUT)) {
                    if (dhl.getRemark() != null && !dhl.getRemark().isEmpty()) {
                        JSONObject json = JSONObject.parseObject(dhl.getRemark());
                        dhl.setInstall(json.getString("install"));
                        dhl.setRecycle(json.getString("recycle"));
                        dhl.setRemark(json.getString("memo"));
                    } else {
                        dhl.setInstall("");
                        dhl.setRecycle("");
                    }
                }
            }
            res.code = 200;
            res.data = dhl;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 单据明细列表
     * @param headerId
     * @param mpList
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getDetailList")
    @ApiOperation(value = "商品明细列表")
    public BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId,
                                          @RequestParam("mpList") String mpList,
                                          @RequestParam(value = "linkType", required = false) String linkType,
                                          @RequestParam(value = "isReadOnly", required = false) String isReadOnly,
                                          HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            List<DepotItemVo4WithInfoEx> dataList = new ArrayList<DepotItemVo4WithInfoEx>();
            if(headerId != 0) {
                dataList = depotItemService.getDetailList(headerId);
            }
            String[] mpArr = mpList.split(",");
            JSONObject outer = new JSONObject();
            outer.put("total", dataList.size());
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                BigDecimal totalOperNumber = BigDecimal.ZERO;
                BigDecimal totalAllPrice = BigDecimal.ZERO;
                BigDecimal totalTaxMoney = BigDecimal.ZERO;
                BigDecimal totalTaxLastMoney = BigDecimal.ZERO;
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("id", diEx.getId());
                    item.put("materialExtendId", diEx.getMaterialExtendId() == null ? "" : diEx.getMaterialExtendId());
                    item.put("barCode", diEx.getBarCode());
                    item.put("name", diEx.getMName());
                    item.put("MNumber", diEx.getMNumber());
                    item.put("categoryName", diEx.getCategoryName());
                    item.put("standard", diEx.getMStandard());
                    item.put("model", diEx.getMModel());
                    item.put("color", diEx.getMColor());
                    item.put("materialOther", getOtherInfo(mpArr, diEx));
                    BigDecimal stock;
                    Unit unitInfo = materialService.findUnit(diEx.getMaterialId()); //查询计量单位信息
                    String materialUnit = diEx.getMaterialUnit();
                    if(StringUtil.isNotEmpty(diEx.getSku())){
                        stock = depotItemService.getSkuStockByParam(diEx.getDepotId(),diEx.getMaterialExtendId(),null,null);
                    } else {
                        Long organId = null;
                        DepotHead depotHead = depotHeadService.getDepotHead(diEx.getDepotId());
                        if(depotHead != null) {
                            organId = depotHead.getOrganId();
                        }

                        stock = depotItemService.getStockByParam(diEx.getDepotId(), diEx.getMaterialId(),null,null, organId);
                        if (StringUtil.isNotEmpty(unitInfo.getName())) {
                            stock = unitService.parseStockByUnit(stock, unitInfo, materialUnit);
                        }
                    }
                    item.put("stock", stock);
                    item.put("unit", diEx.getMaterialUnit());
                    item.put("snList", diEx.getSnList());
                    item.put("batchNumber", diEx.getBatchNumber());
                    item.put("expirationDate", Tools.parseDateToStr(diEx.getExpirationDate()));
                    item.put("sku", diEx.getSku());
                    item.put("enableSerialNumber", diEx.getEnableSerialNumber());
                    item.put("enableBatchNumber", diEx.getEnableBatchNumber());
                    item.put("operNumber", diEx.getOperNumber());
                    item.put("basicNumber", diEx.getBasicNumber());
                    item.put("preNumber", diEx.getOperNumber()); //原数量
                    item.put("finishNumber", depotItemService.getFinishNumber(diEx.getMaterialExtendId(), diEx.getId(), diEx.getHeaderId(), unitInfo, materialUnit, linkType)); //已入庫|已出庫
                    item.put("purchaseDecimal", diEx.getPurchaseDecimal());  //采购价
                    if("basic".equals(linkType)) {
                        //正常情况显示金额，而以销定购的情况不能显示金额
                        item.put("unitPrice", diEx.getUnitPrice());
                        item.put("taxUnitPrice", diEx.getTaxUnitPrice());
                        item.put("allPrice", diEx.getAllPrice());
                        item.put("taxRate", diEx.getTaxRate());
                        item.put("taxMoney", diEx.getTaxMoney());
                        item.put("taxLastMoney", diEx.getTaxLastMoney());
                    }
                    item.put("remark", diEx.getRemark());
                    item.put("linkId", diEx.getLinkId());
                    item.put("depotId", diEx.getDepotId() == null ? "" : diEx.getDepotId());
                    item.put("depotName", diEx.getDepotId() == null ? "" : diEx.getDepotName());
                    item.put("counterId", diEx.getCounterId() == null ? "" : diEx.getCounterId());
                    item.put("counterName", diEx.getCounterName() == null ? "" : diEx.getCounterName());
                    item.put("anotherDepotId", diEx.getAnotherDepotId() == null ? "" : diEx.getAnotherDepotId());
                    item.put("anotherDepotName", diEx.getAnotherDepotId() == null ? "" : diEx.getAnotherDepotName());
                    item.put("mType", diEx.getMaterialType());
                    item.put("op", 1);
                    dataArray.add(item);
                    //合计数据汇总
                    totalOperNumber = totalOperNumber.add(diEx.getOperNumber()==null?BigDecimal.ZERO:diEx.getOperNumber());
                    totalAllPrice = totalAllPrice.add(diEx.getAllPrice()==null?BigDecimal.ZERO:diEx.getAllPrice());
                    totalTaxMoney = totalTaxMoney.add(diEx.getTaxMoney()==null?BigDecimal.ZERO:diEx.getTaxMoney());
                    totalTaxLastMoney = totalTaxLastMoney.add(diEx.getTaxLastMoney()==null?BigDecimal.ZERO:diEx.getTaxLastMoney());
                }
                if(StringUtil.isNotEmpty(isReadOnly) && "1".equals(isReadOnly)) {
                    JSONObject footItem = new JSONObject();
                    footItem.put("operNumber", totalOperNumber);
                    footItem.put("allPrice", totalAllPrice);
                    footItem.put("taxMoney", totalTaxMoney);
                    footItem.put("taxLastMoney", totalTaxLastMoney);
                    dataArray.add(footItem);
                }
            }
            outer.put("rows", dataArray);
            res.code = 200;
            res.data = outer;
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }



    /**
     * 获取扩展信息
     *
     * @return
     */
    public String getOtherInfo(String[] mpArr, DepotItemVo4WithInfoEx diEx)throws Exception {
        String materialOther = "";
        for (int i = 0; i < mpArr.length; i++) {
            if (mpArr[i].equals("制造商")) {
                materialOther = materialOther + ((diEx.getMMfrs() == null || diEx.getMMfrs().equals("")) ? "" : "(" + diEx.getMMfrs() + ")");
            }
            if (mpArr[i].equals("自定义1")) {
                materialOther = materialOther + ((diEx.getMOtherField1() == null || diEx.getMOtherField1().equals("")) ? "" : "(" + diEx.getMOtherField1() + ")");
            }
            if (mpArr[i].equals("自定义2")) {
                materialOther = materialOther + ((diEx.getMOtherField2() == null || diEx.getMOtherField2().equals("")) ? "" : "(" + diEx.getMOtherField2() + ")");
            }
            if (mpArr[i].equals("自定义3")) {
                materialOther = materialOther + ((diEx.getMOtherField3() == null || diEx.getMOtherField3().equals("")) ? "" : "(" + diEx.getMOtherField3() + ")");
            }
        }
        return materialOther;
    }
}
