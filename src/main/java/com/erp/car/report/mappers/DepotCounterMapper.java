package com.erp.car.report.mappers;

import com.erp.car.report.entities.DepotCounter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepotCounterMapper {

    List<DepotCounter> selectByConditionDepot(
            @Param("name") String name,
            @Param("depotId") Long depotId,
            @Param("remark") String remark,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByCounter(
            @Param("name") String name,
            @Param("depotId") Long depotId,
            @Param("remark") String remark);

    DepotCounter selectByPrimaryKey(Long id);

    List<DepotCounter> selectByAll();

    int insertSelective(DepotCounter record);

    int updateByPrimaryKeySelective(DepotCounter record);
}
