package com.erp.car.report.mappers;

import com.erp.car.report.entities.MaterialExtend;
import com.erp.car.report.vo.MaterialExtendVo4List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MaterialExtendMapperEx {

    int batchDeleteMaterialExtendByIds(@Param("ids") String ids[]);

    List<MaterialExtendVo4List> getDetailList(
            @Param("materialId") Long materialId);

    Long getMaxTimeByTenantAndTime(
            @Param("tenantId") Long tenantId,
            @Param("lastTime") Long lastTime,
            @Param("syncNum") Long syncNum);

    List<MaterialExtend> getListByMId(@Param("ids") Long ids[]);

    int batchDeleteMaterialExtendByMIds(@Param("ids") String ids[]);
}