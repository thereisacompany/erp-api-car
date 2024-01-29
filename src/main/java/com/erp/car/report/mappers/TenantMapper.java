package com.erp.car.report.mappers;

import com.erp.car.report.entities.Tenant;
import com.erp.car.report.entities.TenantExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TenantMapper {
    long countByExample(TenantExample example);

    int deleteByExample(TenantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    List<Tenant> selectByExample(TenantExample example);

    Tenant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tenant record, @Param("example") TenantExample example);

    int updateByExample(@Param("record") Tenant record, @Param("example") TenantExample example);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}