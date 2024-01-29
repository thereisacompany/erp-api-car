package com.erp.car.service.sequence;

import com.alibaba.fastjson.JSONObject;
import com.erp.car.constants.BusinessConstants;
import com.erp.car.report.entities.SerialNumber;
import com.erp.car.report.entities.SerialNumberEx;
import com.erp.car.report.mappers.SequenceMapperEx;
import com.erp.car.exception.JshException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Description
 *
 * @Author: jishenghua
 * @Date: 2021/3/16 16:33
 */
@Service
public class SequenceService {
    private Logger logger = LoggerFactory.getLogger(SequenceService.class);

    @Resource
    private SequenceMapperEx sequenceMapperEx;

    public SerialNumber getSequence(long id)throws Exception {
        return null;
    }

    public List<SerialNumberEx> select(String name, Integer offset, Integer rows)throws Exception {
        return null;
    }

    public Long countSequence(String name)throws Exception {
        return null;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertSequence(JSONObject obj, HttpServletRequest request)throws Exception {
        return 0;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateSequence(JSONObject obj, HttpServletRequest request) throws Exception{
        return 0;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteSequence(Long id, HttpServletRequest request)throws Exception {
        return 0;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteSequence(String ids, HttpServletRequest request)throws Exception {
        return 0;
    }

    public int checkIsNameExist(Long id, String serialNumber)throws Exception {
        return 0;
    }

    /**
     * 創建一個唯一的序列號
     * */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public String buildOnlyNumber(String seqName)throws Exception{
        Long buildOnlyNumber=null;
        synchronized (this){
            try{
                sequenceMapperEx.updateBuildOnlyNumber(seqName); //编号+1
                buildOnlyNumber= sequenceMapperEx.getBuildOnlyNumber(seqName);
            }catch(Exception e){
                JshException.writeFail(logger, e);
            }
        }
        if(buildOnlyNumber<BusinessConstants.SEQ_TO_STRING_MIN_LENGTH){
            StringBuffer sb=new StringBuffer(buildOnlyNumber.toString());
            int len=BusinessConstants.SEQ_TO_STRING_MIN_LENGTH.toString().length()-sb.length();
            for(int i=0;i<len;i++){
                sb.insert(0,BusinessConstants.SEQ_TO_STRING_LESS_INSERT);
            }
            return sb.toString();
        }else{
            return buildOnlyNumber.toString();
        }
    }

    /**
     * @param isDO 是否為配送單
     * @return
     */
    public String buildNumber(Boolean isDO){
        if(isDO != null) {
            if(isDO) {
                return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            }
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
