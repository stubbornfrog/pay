package niuwajun.commmon.model;

/**
 * 业务错误（返回给接口调用者，无须记录日志）
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String message;
	String error;

	/**
	 * 新建业务错误
	 * 
	 * @param message 错误提示
	 */
	public BusinessException(String message) {
		this.message = message;
	}

	/**
	 * 新建业务错误
	 * 
	 * @param message 错误提示
	 * @param error 错误描述
	 */
	public BusinessException(String message, String error) {
		this.message = message;
		this.error = error;
	}

	/**
	 * 获取错误提示
	 * 
	 * @return 错误提示
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置错误提示
	 * 
	 * @param message 错误提示
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取错误描述
	 * 
	 * @return 错误描述
	 */
	public String getError() {
		return error;
	}

	/**
	 * 设置错误描述
	 * 
	 * @param error 错误描述
	 */
	public void setError(String error) {
		this.error = error;
	}

}
