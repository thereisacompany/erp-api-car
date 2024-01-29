package com.erp.car.report.mappers;

import com.erp.car.report.entities.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface SupplierMapperEx {

    List<Supplier> selectByConditionSupplier(
            @Param("supplier") String supplier,
            @Param("type") String type,
            @Param("phonenum") String phonenum,
            @Param("telephone") String telephone,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsBySupplier(
            @Param("supplier") String supplier,
            @Param("type") String type,
            @Param("phonenum") String phonenum,
            @Param("telephone") String telephone);

    List<Supplier> findByAll(
            @Param("supplier") String supplier,
            @Param("type") String type,
            @Param("phonenum") String phonenum,
            @Param("telephone") String telephone);

    int batchDeleteSupplierByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    Supplier getSupplierByNameAndType(
            @Param("supplier") String supplier,
            @Param("type") String type);
}