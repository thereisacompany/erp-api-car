package com.erp.car.report.mappers;

import com.erp.car.report.entities.MaterialInitialStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MaterialInitialStockMapperEx {

    int batchInsert(List<MaterialInitialStock> list);

}