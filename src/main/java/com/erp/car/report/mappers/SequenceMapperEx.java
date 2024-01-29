package com.erp.car.report.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SequenceMapperEx {

    void updateBuildOnlyNumber(@Param("seq_name") String seq_name);

    /**
     * 获得一个全局唯一的数作为订单号的追加
     * */
    Long getBuildOnlyNumber(@Param("seq_name") String seq_name);
}
