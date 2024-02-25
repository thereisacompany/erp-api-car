package com.erp.car.service.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erp.car.constants.BusinessConstants;
import com.erp.car.constants.ExceptionConstants;
import com.erp.car.report.entities.*;
import com.erp.car.report.mappers.UserMapper;
import com.erp.car.report.mappers.UserMapperEx;
import com.erp.car.report.vo.TreeNodeEx;
import com.erp.car.exception.BusinessParamCheckingException;
import com.erp.car.exception.BusinessRunTimeException;
import com.erp.car.exception.JshException;
import com.erp.car.service.functions.FunctionService;
import com.erp.car.service.log.LogService;
import com.erp.car.service.orgaUserRel.OrgaUserRelService;
import com.erp.car.service.redis.RedisService;
import com.erp.car.service.role.RoleService;
import com.erp.car.service.tenant.TenantService;
import com.erp.car.service.userBusiness.UserBusinessService;
import com.erp.car.utils.ExceptionCodeConstants;
import com.erp.car.utils.StringUtil;
import com.erp.car.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserMapperEx userMapperEx;
    @Resource
    private OrgaUserRelService orgaUserRelService;
    @Resource
    private LogService logService;
    @Resource
    private UserService userService;
    @Resource
    private TenantService tenantService;
    @Resource
    private UserBusinessService userBusinessService;
    @Resource
    private RoleService roleService;
    @Resource
    private FunctionService functionService;
    @Resource
    private RedisService redisService;

    public User getUser(long id)throws Exception {
        User result=null;
        try{
            result=userMapper.selectByPrimaryKey(id);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return result;
    }

    public UserCar getCarUser(long id)throws Exception {
        UserCar result=null;
        try{
            result=userMapper.selectCarByPrimaryKey(id);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return result;
    }


    public List<User> getUserListByIds(String ids)throws Exception {
        List<Long> idList = StringUtil.strToLongList(ids);
        List<User> list = new ArrayList<>();
        try{
            UserExample example = new UserExample();
            example.createCriteria().andIdIn(idList);
            list = userMapper.selectByExample(example);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return list;
    }

    public List<User> getUser()throws Exception {
        UserExample example = new UserExample();
        example.createCriteria().andStatusEqualTo(BusinessConstants.USER_STATUS_NORMAL);
        List<User> list=null;
        try{
            list=userMapper.selectByExample(example);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return list;
    }

//    public List<UserEx> select(String userName, String loginName, int offset, int rows)throws Exception {
//        List<UserEx> list=null;
//        try{
//            list=userMapperEx.selectByConditionUser(userName, loginName, offset, rows);
//            for(UserEx ue: list){
//                String userType = "";
//                if (ue.getId().equals(ue.getTenantId())) {
//                    userType = "租户";
//                } else if(ue.getTenantId() == null){
//                    userType = "超管";
//                } else {
//                    userType = "普通";
//                }
//                ue.setUserType(userType);
//            }
//        }catch(Exception e){
//            JshException.readFail(logger, e);
//        }
//        return list;
//    }

    public Long countUser(String userName, String loginName)throws Exception {
        Long result=null;
        try{
            result=userMapperEx.countsByUser(userName, loginName);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return result;
    }
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:30
     * @Param: beanJson
     * @Param: request
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertUser(JSONObject obj, HttpServletRequest request)throws Exception {
        User user = JSONObject.parseObject(obj.toJSONString(), User.class);
        String password = "123456";
        //因密码用MD5加密，需要对密码进行转化
        try {
            password = Tools.md5Encryp(password);
            user.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>>>>>转化MD5字符串错误 ：" + e.getMessage());
        }
        int result=0;
        try{
            result=userMapper.insertSelective(user);
            logService.insertLog("用户",
                    new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_ADD).append(user.getLoginName()).toString(), request);
        }catch(Exception e){
            JshException.writeFail(logger, e);
        }
        return result;
    }
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:31
     * @Param: beanJson
     * @Param: id
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUser(JSONObject obj, HttpServletRequest request) throws Exception{
        User user = JSONObject.parseObject(obj.toJSONString(), User.class);
        int result=0;
        try{
            result=userMapper.updateByPrimaryKeySelective(user);
            logService.insertLog("用户",
                    new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_EDIT).append(user.getLoginName()).toString(), request);
        }catch(Exception e){
            JshException.writeFail(logger, e);
        }
        return result;
    }
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:32
     * @Param: user
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUserByObj(User user) throws Exception{
        logService.insertLog("用户",
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_EDIT).append(user.getId()).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        int result=0;
        try{
            result=userMapper.updateByPrimaryKeySelective(user);
        }catch(Exception e){
            JshException.writeFail(logger, e);
        }
        return result;
    }
    /**
     * create by: cjl
     * description:
     *  添加事务控制
     * create time: 2019/1/11 14:33
     * @Param: md5Pwd
     * @Param: id
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int resetPwd(String md5Pwd, Long id) throws Exception{
        int result=0;
        logService.insertLog("用户",
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_EDIT).append(id).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User u = getUser(id);
        String loginName = u.getLoginName();
        if("admin".equals(loginName)){
            logger.info("禁止重置超管密码");
        } else {
            User user = new User();
            user.setId(id);
            user.setPassword(md5Pwd);
            try{
                result=userMapper.updateByPrimaryKeySelective(user);
            }catch(Exception e){
                JshException.writeFail(logger, e);
            }
        }
        return result;
    }

//    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
//    public int deleteUser(Long id, HttpServletRequest request)throws Exception {
//        return batDeleteUser(id.toString());
//    }
//
//    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
//    public int batchDeleteUser(String ids, HttpServletRequest request)throws Exception {
//        return batDeleteUser(ids);
//    }
//
//    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
//    public int batDeleteUser(String ids) throws Exception{
//        int result=0;
//        StringBuffer sb = new StringBuffer();
//        sb.append(BusinessConstants.LOG_OPERATION_TYPE_DELETE);
//        List<User> list = getUserListByIds(ids);
//        for(User user: list){
//            if(user.getId().equals(user.getTenantId())) {
//                logger.error("异常码[{}],异常提示[{}],参数,ids:[{}]",
//                        ExceptionConstants.USER_LIMIT_TENANT_DELETE_CODE,ExceptionConstants.USER_LIMIT_TENANT_DELETE_MSG,ids);
//                throw new BusinessRunTimeException(ExceptionConstants.USER_LIMIT_TENANT_DELETE_CODE,
//                        ExceptionConstants.USER_LIMIT_TENANT_DELETE_MSG);
//            }
//            sb.append("[").append(user.getLoginName()).append("]");
//        }
//        logService.insertLog("用户", sb.toString(),
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
//        String idsArray[]=ids.split(",");
//        try{
//            result=userMapperEx.batDeleteOrUpdateUser(idsArray,BusinessConstants.USER_STATUS_DELETE);
//        }catch(Exception e){
//            JshException.writeFail(logger, e);
//        }
//        if(result<1){
//            logger.error("异常码[{}],异常提示[{}],参数,ids:[{}]",
//                    ExceptionConstants.USER_DELETE_FAILED_CODE,ExceptionConstants.USER_DELETE_FAILED_MSG,ids);
//            throw new BusinessRunTimeException(ExceptionConstants.USER_DELETE_FAILED_CODE,
//                    ExceptionConstants.USER_DELETE_FAILED_MSG);
//        }
//        return result;
//    }

    public int validateUser(String loginName, String password) throws Exception {
        /**默认是可以登录的*/
        List<User> list = null;
        try {
            UserExample example = new UserExample();
            example.createCriteria().andLoginNameEqualTo(loginName);
            list = userMapper.selectByExample(example);

            if (null != list && list.size() == 0) {
                return ExceptionCodeConstants.UserExceptionCode.USER_NOT_EXIST;
            } else if(list.size() ==1) {
                if(list.get(0).getStatus()!=0) {
                    return ExceptionCodeConstants.UserExceptionCode.BLACK_USER;
                }
//                Long tenantId = list.get(0).getTenantId();
//                Tenant tenant = tenantService.getTenantByTenantId(tenantId);
//                if(tenant!=null) {
//                    if(tenant.getEnabled()!=null && !tenant.getEnabled()) {
//                        return ExceptionCodeConstants.UserExceptionCode.BLACK_TENANT;
//                    }
//                    if(tenant.getExpireTime()!=null && tenant.getExpireTime().getTime()<System.currentTimeMillis()){
//                        return ExceptionCodeConstants.UserExceptionCode.EXPIRE_TENANT;
//                    }
//                }
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>访问验证用户姓名是否存在后台信息异常", e);
            return ExceptionCodeConstants.UserExceptionCode.USER_ACCESS_EXCEPTION;
        }
        try {
            UserExample example = new UserExample();
            example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password)
                    .andPlusStatusEqualTo(BusinessConstants.USER_STATUS_NORMAL);
            list = userMapper.selectByExample(example);
            if (null != list && list.size() == 0) {
                return ExceptionCodeConstants.UserExceptionCode.USER_PASSWORD_ERROR;
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>访问验证用户密码后台信息异常", e);
            return ExceptionCodeConstants.UserExceptionCode.USER_ACCESS_EXCEPTION;
        }
        return ExceptionCodeConstants.UserExceptionCode.USER_CONDITION_FIT;
    }

    public User getUserByLoginName(String loginName)throws Exception {
        UserExample example = new UserExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPlusStatusEqualTo(BusinessConstants.USER_STATUS_NORMAL);
        List<User> list=null;
        try{
            list= userMapper.selectByExample(example);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        User user =null;
        if(list!=null&&list.size()>0){
            user = list.get(0);
        }
        return user;
    }

    public int checkIsNameExist(Long id, String name)throws Exception {
        UserExample example = new UserExample();
        List <Byte> userStatus=new ArrayList<Byte>();
        userStatus.add(BusinessConstants.USER_STATUS_DELETE);
        userStatus.add(BusinessConstants.USER_STATUS_BANNED);
        example.createCriteria().andIdNotEqualTo(id).andLoginNameEqualTo(name).andStatusNotIn(userStatus);
        List<User> list=null;
        try{
            list= userMapper.selectByExample(example);
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return list==null?0:list.size();
    }
    /**
     * create by: cjl
     * description:
     *  获取当前用户信息
     * create time: 2019/1/24 10:01
     * @Param:
     * @return com.jsh.erp.datasource.entities.User
     */
    public User getCurrentUser()throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object obj = redisService.getObjectFromSessionByKey(request,"userId");
        if(obj == null) {
            return null;
        }
        Long userId = Long.parseLong(obj.toString());
        return getUser(userId);
    }

    /**
     * 根据用户名查询id
     * @param loginName
     * @return
     */
    public Long getIdByLoginName(String loginName) {
        Long userId = 0L;
        UserExample example = new UserExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andStatusEqualTo(BusinessConstants.USER_STATUS_NORMAL);
        List<User> list = userMapper.selectByExample(example);
        if(list!=null) {
            userId = list.get(0).getId();
        }
        return userId;
    }

//    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
//    public void addUserAndOrgUserRel(UserEx ue, HttpServletRequest request) throws Exception{
//        if(BusinessConstants.DEFAULT_MANAGER.equals(ue.getLoginName())) {
//            throw new BusinessRunTimeException(ExceptionConstants.USER_NAME_LIMIT_USE_CODE,
//                    ExceptionConstants.USER_NAME_LIMIT_USE_MSG);
//        } else {
//            logService.insertLog("用户",
//                    BusinessConstants.LOG_OPERATION_TYPE_ADD,
//                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
//            //检查用户名和登录名
//            checkUserNameAndLoginName(ue);
//            //新增用户信息
//            ue= this.addUser(ue);
//            if(ue==null){
//                logger.error("异常码[{}],异常提示[{}],参数,[{}]",
//                        ExceptionConstants.USER_ADD_FAILED_CODE,ExceptionConstants.USER_ADD_FAILED_MSG);
//                throw new BusinessRunTimeException(ExceptionConstants.USER_ADD_FAILED_CODE,
//                        ExceptionConstants.USER_ADD_FAILED_MSG);
//            }
//            //用户id，根据用户名查询id
//            Long userId = getIdByLoginName(ue.getLoginName());
//            if(ue.getRoleId()!=null){
//                JSONObject ubObj = new JSONObject();
//                ubObj.put("type", "UserRole");
//                ubObj.put("keyid", userId);
//                ubObj.put("value", "[" + ue.getRoleId() + "]");
//                userBusinessService.insertUserBusiness(ubObj, request);
//            }
//            if(ue.getOrgaId()==null){
//                //如果没有选择机构，就不建机构和用户的关联关系
//                return;
//            }
//            //新增用户和机构关联关系
//            OrgaUserRel oul=new OrgaUserRel();
//            //机构id
//            oul.setOrgaId(ue.getOrgaId());
//            oul.setUserId(userId);
//            //用户在机构中的排序
//            oul.setUserBlngOrgaDsplSeq(ue.getUserBlngOrgaDsplSeq());
//            oul=orgaUserRelService.addOrgaUserRel(oul);
//            if(oul==null){
//                logger.error("异常码[{}],异常提示[{}],参数,[{}]",
//                        ExceptionConstants.ORGA_USER_REL_ADD_FAILED_CODE,ExceptionConstants.ORGA_USER_REL_ADD_FAILED_MSG);
//                throw new BusinessRunTimeException(ExceptionConstants.ORGA_USER_REL_ADD_FAILED_CODE,
//                        ExceptionConstants.ORGA_USER_REL_ADD_FAILED_MSG);
//            }
//        }
//    }







    /**
     * 获取用户id
     * @param request
     * @return
     */
    public Long getUserId(HttpServletRequest request) throws Exception{
        Object userIdObj = redisService.getObjectFromSessionByKey(request,"userId");
        Long userId = null;
        if(userIdObj != null) {
            userId = Long.parseLong(userIdObj.toString());
        }
        return userId;
    }

    /**
     * 用户的按钮权限
     * @param userId
     * @return
     * @throws Exception
     */
    public JSONArray getBtnStrArrById(Long userId) throws Exception {
        JSONArray btnStrArr = new JSONArray();
        List<UserBusiness> userRoleList = userBusinessService.getBasicData(userId.toString(), "UserRole");
        if(userRoleList!=null && userRoleList.size()>0) {
            String roleValue = userRoleList.get(0).getValue();
            if(StringUtil.isNotEmpty(roleValue) && roleValue.indexOf("[")>-1 && roleValue.indexOf("]")>-1){
                roleValue = roleValue.replace("[", "").replace("]", ""); //角色id-单个
                List<UserBusiness> roleFunctionsList = userBusinessService.getBasicData(roleValue, "RoleFunctions");
                if(roleFunctionsList!=null && roleFunctionsList.size()>0) {
                    String btnStr = roleFunctionsList.get(0).getBtnStr();
                    if(StringUtil.isNotEmpty(btnStr)){
                        btnStrArr = JSONArray.parseArray(btnStr);
                    }
                }
            }
        }
        //将数组中的funId转为url
        JSONArray btnStrWithUrlArr = new JSONArray();
        if(btnStrArr.size()>0) {
            List<Function> functionList = functionService.getFunction();
            Map<Long, String> functionMap = new HashMap<>();
            for (Function function: functionList) {
                functionMap.put(function.getId(), function.getUrl());
            }
            for (Object obj : btnStrArr) {
                JSONObject btnStrObj = JSONObject.parseObject(obj.toString());
                Long funId = btnStrObj.getLong("funId");
                JSONObject btnStrWithUrlObj = new JSONObject();
                btnStrWithUrlObj.put("url", functionMap.get(funId));
                btnStrWithUrlObj.put("btnStr", btnStrObj.getString("btnStr"));
                btnStrWithUrlArr.add(btnStrWithUrlObj);
            }
        }
        return btnStrWithUrlArr;
    }

//    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
//    public int batchSetStatus(Byte status, String ids, HttpServletRequest request)throws Exception {
//        int result=0;
//        List<User> list = getUserListByIds(ids);
//        //选中的用户的数量
//        int selectUserSize = list.size();
//        //查询启用状态的用户的数量
//        int enableUserSize = getUser().size();
//        long userNumLimit = Long.parseLong(redisService.getObjectFromSessionByKey(request,"userNumLimit").toString());
//        if(selectUserSize + enableUserSize > userNumLimit && status == 0) {
//            throw new BusinessParamCheckingException(ExceptionConstants.USER_ENABLE_OVER_LIMIT_FAILED_CODE,
//                    ExceptionConstants.USER_ENABLE_OVER_LIMIT_FAILED_MSG);
//        }
//        StringBuilder userStr = new StringBuilder();
//        List<Long> idList = new ArrayList<>();
//        for(User user: list) {
//            if(user.getId().equals(user.getTenantId())) {
//                //租户不能进行禁用
//            } else {
//                idList.add(user.getId());
//                userStr.append(user.getLoginName()).append(" ");
//            }
//        }
//        String statusStr ="";
//        if(status == 0) {
//            statusStr ="批量启用";
//        } else if(status == 2) {
//            statusStr ="批量禁用";
//        }
//        if(idList.size()>0) {
//            User user = new User();
//            user.setStatus(status);
//            UserExample example = new UserExample();
//            example.createCriteria().andIdIn(idList);
//            result = userMapper.updateByExampleSelective(user, example);
//            logService.insertLog("用户",
//                    new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_EDIT).append(userStr).append("-").append(statusStr).toString(),
//                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
//        } else {
//            result = 1;
//        }
//        return result;
//    }
    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public Role getRoleTypeByUserId(long userId) throws Exception {
        Role role = new Role();
        List<UserBusiness> list = userBusinessService.getBasicData(String.valueOf(userId), "UserRole");
        UserBusiness ub = null;
        if(list.size() > 0) {
            ub = list.get(0);
            String values = ub.getValue();
            String roleId = null;
            if(values!=null) {
                values = values.replaceAll("\\[\\]",",").replace("[","").replace("]","");
            }
            String [] valueArray=values.split(",");
            if(valueArray.length>0) {
                roleId = valueArray[0];
            }
            role = roleService.getRoleWithoutTenant(Long.parseLong(roleId));
        }
        return role;
    }
}
