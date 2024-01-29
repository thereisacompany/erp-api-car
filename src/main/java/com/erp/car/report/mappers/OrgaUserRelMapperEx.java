package com.erp.car.report.mappers;

import com.erp.car.report.entities.OrgaUserRel;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/3/12 9:13
 */
@Mapper
public interface OrgaUserRelMapperEx {

    int addOrgaUserRel(OrgaUserRel orgaUserRel);

    int updateOrgaUserRel(OrgaUserRel orgaUserRel);
}
