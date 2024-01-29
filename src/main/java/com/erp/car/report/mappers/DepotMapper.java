package com.erp.car.report.mappers;

import com.erp.car.report.entities.Depot;
import com.erp.car.report.entities.DepotExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepotMapper {
    long countByExample(DepotExample example);

    int deleteByExample(DepotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    int insertSelective(Depot record);

    List<Depot> selectByExample(DepotExample example);

    Depot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Depot record, @Param("example") DepotExample example);

    int updateByExample(@Param("record") Depot record, @Param("example") DepotExample example);

    int updateByPrimaryKeySelective(Depot record);

    int updateByPrimaryKey(Depot record);
}