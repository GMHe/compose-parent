package cn.compose.admin.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {

    /**
     * 接口响应码，0：成功，1：失败
     */
    public static final int RESPONSE_CODE_SUCCESS = 0;
    /**
     * 接口响应码，0：成功，1：失败
     */
    public static final int RESPONSE_CODE_FAILURE = 1;

    public static final String redis_add_hashKey = "account:add";


    /**
     * 日志接口操作响应码，0：成功，1：失败
     */
    public static final int LOG_CODE_SUCCESS = 0;
    /**
     * 日志接口操作响应码，0：成功，1：失败
     */
    public static final int LOG_CODE_FAILURE = 1;

    /**
     * 日志接口资源档案对应的功能编码
     */
    public static final String LOG_FUNC_ID = "P07010201";

    /**
     * 日志接口资源档案对应的功能描述
     */
    public static final String LOG_FUNC_DESC = "资源档案";

    /**
     * 数据校验状态，1：校验通过，2：校验不通过，3：同步资源管理中心失败
     */
    public static final int CHECK_STATUS_SUCCESS = 1;
    /**
     * 数据校验状态，1：校验通过，2：校验不通过，3：同步资源管理中心失败
     */
    public static final int CHECK_STATUS_FAILURE = 2;
    /**
     * 数据校验状态，1：校验通过，2：校验不通过，3：同步资源管理中心失败
     */
    public static final int CHECK_STATUS_SYNC_FAILURE = 3;
    /**
     * 关闭webSocket连接消息状态
     */
    public static final String WEBSOCKET_STATUS_MSG = "close";
    public static final String WEBSOCKET_CHANNEL = "channel:tableWebSocketHandler";
    public static final String SYNC_MGR_UPDATE_LIST_CACHE = "channel:SYNC_MGR_UPDATE_LIST_CACHE";
    //设备信息：device
    public static final String SYNC_MGR_TYPE_DEVICE = "device";
    //平台信息：platformInfo
    public static final String SYNC_MGR_TYPE_PLATFORMINFO = "platformInfo";


    /**
     * 档案表通用字段
     */
    public static final String[] COMMON_FIELDS_FOR_ADD = {"UUID", "CREATE_TIME", "UPDATE_TIME", "IS_UPLOAD",
            "PLAT_FLAG", "IS_DELETE", "DATA_VERSION", "XZQH"};

    /**
     * 档案表通用字段
     */
    public static final String[] COMMON_FIELDS_FOR_UPDATE = {"UUID", "UPDATE_TIME", "IS_UPLOAD",
            "PLAT_FLAG", "IS_DELETE", "DATA_VERSION", "XZQH"};

    /**
     * 审核表不需要的通用字段
     */
    public static final Set<String> AUDIT_EXCLUDE_FIELD_SET = new HashSet<String>(Arrays.asList("IS_UPLOAD,IS_DELETE,DATA_VERSION".split(",")));
}
