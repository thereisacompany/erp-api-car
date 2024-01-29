package com.erp.car.report.mappers;

import com.erp.car.report.entities.DepotItem;
import com.erp.car.report.entities.DepotItemExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepotItemMapper {
    long countByExample(DepotItemExample example);

    int deleteByExample(DepotItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepotItem record);

    int insertSelective(DepotItem record);

    List<DepotItem> selectByExample(DepotItemExample example);

    DepotItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    int updateByExample(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    int updateByPrimaryKeySelective(DepotItem record);

    int updateByPrimaryKey(DepotItem record);
}