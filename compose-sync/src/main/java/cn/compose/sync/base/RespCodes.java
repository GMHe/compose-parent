package cn.compose.sync.base;

/**
 * 请参考"服务响应码规范":
 * @author elvis.xu
 * @since 2019-02-26 10:24
 */
public interface RespCodes {
	String OK = "00000";
	
	// 服务内部异常
	String NETWORK_TIMEOUT = "10000";// 超时异常
	String INTERNAL_ERROR = "10001";// 服务内部异常
	String SERVICE_NOT_AVAILABLE = "10002";// 服务不可用
	String SERVICE_TIMEOUT = "10003";// 服务处理超时
	String DEPENDENCY_NOT_AVAILABLE = "10004";// 依赖资源不可用

	// 参数校验错误
	String PARAM_ERROR = "10010";// 参数通用错误
	String PARAM_REQUIRED = "10011";// 参数不能为空
	String PARAM_INVALID = "10012";// 参数值不合法
	String PARAM_OUT_OF_RANGE = "10013";// 参数值超出范围
	String PARAM_TOO_LONG = "10014";// 参数值过长
	String PARAM_TYPE_MISMATCH = "10015";// 参数类型不符
	
	// 安全异常
	String AUTHORIZATION_FAILURE = "11000";// 授权失败
	String PERMISSION_DENIED = "11001";// 无权限访问
	String ACCESS_FREQUENCY_LIMIT = "11002";// 访问频率过高
	String REQUEST_REJECT = "11003";// 拒绝请求
	String IP_ILLEGAL = "11004";// IP非法
	String AUTHORIZATION_EXPIRED = "11005";// 授权已过期
	String ACCOUNT_DISABLE = "11006";// 账号已禁用
	String ACCOUNT_PASSWORD_ERROR = "11007";// 账号名密码错误
	String TOKEN_ERROR = "11008";// token错误
}
