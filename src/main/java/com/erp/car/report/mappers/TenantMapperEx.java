package com.erp.car.report.mappers;

import com.erp.car.report.entities.TenantEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TenantMapperEx {

    List<TenantEx> selectByConditionTenant(
            @Param("loginName") String loginName,
            @Param("type") String type,
            @Param("enabled") String enabled,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByTenant(
            @Param("loginName") String loginName,
            @Param("type") String type,
            @Param("enabled") String enabled);
}