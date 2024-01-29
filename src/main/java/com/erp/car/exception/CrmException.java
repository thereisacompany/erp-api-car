package com.erp.car.exception;

import com.erp.car.constants.ExceptionConstants;
import org.slf4j.Logger;

public class CrmException {

    public static void readFail(Logger logger, Exception e) {
        logger.error("異常碼[{}],異常提示[{}],参數,[{}]", ExceptionConstants.DATA_READ_FAIL_CODE,
                ExceptionConstants.DATA_READ_FAIL_MSG, e);
        throw new BusinessRunTimeException(ExceptionConstants.DATA_READ_FAIL_CODE,
                ExceptionConstants.DATA_READ_FAIL_MSG);
    }

    public static void writeFail(Logger logger, Exception e) {
        logger.error("異常碼[{}],異常提示[{}],参數,[{}]", ExceptionConstants.DATA_WRITE_FAIL_CODE,
                ExceptionConstants.DATA_WRITE_FAIL_MSG, e);
        throw new BusinessRunTimeException(ExceptionConstants.DATA_WRITE_FAIL_CODE,
                ExceptionConstants.DATA_WRITE_FAIL_MSG);
    }
}
