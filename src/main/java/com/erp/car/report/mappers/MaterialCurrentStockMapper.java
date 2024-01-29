package com.erp.car.report.mappers;

import com.erp.car.report.entities.MaterialCurrentStock;
import com.erp.car.report.entities.MaterialCurrentStockExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MaterialCurrentStockMapper {
    long countByExample(MaterialCurrentStockExample example);

    int deleteByExample(MaterialCurrentStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialCurrentStock record);

    int insertSelective(MaterialCurrentStock record);

    List<MaterialCurrentStock> selectByExample(MaterialCurrentStockExample example);

    MaterialCurrentStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialCurrentStock record, @Param("example") MaterialCurrentStockExample example);

    int updateByExample(@Param("record") MaterialCurrentStock record, @Param("example") MaterialCurrentStockExample example);

    int updateByPrimaryKeySelective(MaterialCurrentStock record);

    int updateByPrimaryKey(MaterialCurrentStock record);
}