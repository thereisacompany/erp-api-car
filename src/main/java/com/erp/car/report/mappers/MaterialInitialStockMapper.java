package com.erp.car.report.mappers;

import com.erp.car.report.entities.MaterialInitialStock;
import com.erp.car.report.entities.MaterialInitialStockExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MaterialInitialStockMapper {
    long countByExample(MaterialInitialStockExample example);

    int deleteByExample(MaterialInitialStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialInitialStock record);

    int insertSelective(MaterialInitialStock record);

    List<MaterialInitialStock> selectByExample(MaterialInitialStockExample example);

    MaterialInitialStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialInitialStock record, @Param("example") MaterialInitialStockExample example);

    int updateByExample(@Param("record") MaterialInitialStock record, @Param("example") MaterialInitialStockExample example);

    int updateByPrimaryKeySelective(MaterialInitialStock record);

    int updateByPrimaryKey(MaterialInitialStock record);
}