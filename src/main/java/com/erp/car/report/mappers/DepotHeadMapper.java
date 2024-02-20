package com.erp.car.report.mappers;

import com.erp.car.report.entities.CarOrderStatus;
import com.erp.car.report.entities.DepotHead;
import com.erp.car.report.entities.DepotHeadExample;
import com.erp.car.report.vo.DeliveryStatus;
import com.erp.car.report.vo.DepotHeadDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepotHeadMapper {
    long countByExample(DepotHeadExample example);

    int deleteByExample(DepotHeadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepotHead record);

    int insertSelective(DepotHead record);

    List<DepotHead> selectByExample(DepotHeadExample example);

    DepotHead selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepotHead record, @Param("example") DepotHeadExample example);

    int updateByExample(@Param("record") DepotHead record, @Param("example") DepotHeadExample example);

    int updateByPrimaryKeySelective(DepotHead record);

    int updateByPrimaryKey(DepotHead record);

    int updateByCarOrderStatus(@Param("userid") Long userid, CarOrderStatus carOrderStatus);

    DepotHeadDetail selectHeaderDetailByHeaderId(@Param("headerId") Long headerId,
                                                 @Param("itemId") Long itemId);

    List<DeliveryStatus> selectDetailRecord(@Param("detailId") Long detailId);
}