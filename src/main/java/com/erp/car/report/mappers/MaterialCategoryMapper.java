package com.erp.car.report.mappers;

import com.erp.car.report.entities.MaterialCategory;
import com.erp.car.report.entities.MaterialCategoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MaterialCategoryMapper {
    long countByExample(MaterialCategoryExample example);

    int deleteByExample(MaterialCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategory record);

    int insertSelective(MaterialCategory record);

    List<MaterialCategory> selectByExample(MaterialCategoryExample example);

    MaterialCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialCategory record, @Param("example") MaterialCategoryExample example);

    int updateByExample(@Param("record") MaterialCategory record, @Param("example") MaterialCategoryExample example);

    int updateByPrimaryKeySelective(MaterialCategory record);

    int updateByPrimaryKey(MaterialCategory record);
}