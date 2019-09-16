package niuwajun.commmon.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 对请求中的参数进行包装<br>
 * 所有请求中的参数都将自动封装，但不包括上传文件操作中的文件。
 */
public class RequestParamMap {

	static final long PAGE_LIMIT = 1000; // 默认一页记录数

	/**
	 * 参数map
	 */
	private Map<String, String> paramMap = new HashMap<String, String>();

	/**
	 * 默认构造函数
	 * 
	 * @param request 请求对象
	 */
	public RequestParamMap() {

	}

	/**
	 * 重载构造函数，将HttpServletRequest中的参数包装成map
	 * 
	 * @param request 请求对象
	 */
	@SuppressWarnings("unchecked")
	public RequestParamMap(HttpServletRequest request) throws Exception {
		try {
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				if (paramValue != null && paramValue.trim().length() > 0) {
					paramMap.put(paramName, paramValue.trim());
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取参数map对象
	 * 
	 * @return
	 */
	public Map<String, String> getMap() {
		return paramMap;
	}

	/**
	 * 判断paramMap中是否存在指定参数<br>
	 * 若指定参数值不为空，返回true<br>
	 * 若指定参数值为null或空，返回false
	 * 
	 * @param paramName 参数名
	 * @return
	 */
	public boolean containsKey(String paramName) {
		if (paramMap.containsKey(paramName)) {
			String value = paramMap.get(paramName);
			if (value != null && value.trim().length() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 获取paramMap中指定参数<br>
	 * 参数值为null或空时，均返回空字符串
	 * 
	 * @param paramName 参数名
	 * @return 参数值
	 */
	public String get(String paramName) {
		if (paramMap.containsKey(paramName)) {
			String value = paramMap.get(paramName);
			if (value != null && value.trim().length() > 0) {
				return value;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 获取paramMap中指定参数<br>
	 * 参数值为null或空时，均返回空字符串
	 * 
	 * @param paramName 参数名
	 * @return 参数值
	 */
	public String getNotNullValue(String paramName) throws Exception{
		if (paramMap.containsKey(paramName)) {
			String value = paramMap.get(paramName);
			if (value != null && value.trim().length() > 0) {
				return value;
			} else {
				throw new BusinessException("参数:"+paramName+"不能为空");
			}
		} else {
			throw new BusinessException("参数:"+paramName+"不能为空");
		}
	}
	
	/**
	 * 获取paramMap中指定参数，包装成“%value%”形式，方便进行like查询<br>
	 * 参数值为null或空时，均返回空字符串
	 * 
	 * @param paramName 参数名
	 * @return 参数值
	 */
	public String getLike(String paramName) {
		return "%" + get(paramName) + "%";
	}

	/**
	 * 将新参数放入paramMap
	 * 
	 * @param paramName 参数名
	 * @param paramValue 参数值
	 * @return
	 */
	public void put(String paramName, Object paramValue) {
		paramMap.put(paramName, paramValue.toString());
	}

	/**
	 * 清空paramMap
	 * 
	 * @return
	 */
	public void clear() {
		paramMap.clear();
	}

	/**
	 * 获取paramMap中的start参数<br>
	 * 若不存在，返回0
	 * 
	 * @return
	 */
	public long getStart() {
		try {
			String startName = "start";
			if (paramMap.containsKey(startName)) {
				String value = paramMap.get(startName);
				if (value != null && value.trim().length() > 0) {
					return Long.parseLong(value);
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取paramMap中的limit参数<br>
	 * 若不存在，返回配置文件中配置的每页记录数
	 * 
	 * @return
	 */
	public long getLimit() {
		try {
			String limitName = "limit";
			if (paramMap.containsKey(limitName)) {
				String value = paramMap.get(limitName);
				if (value != null && value.trim().length() > 0) {
					return Long.parseLong(value);
				} else {
					return PAGE_LIMIT;
				}
			} else {
				return PAGE_LIMIT;
			}
		} catch (Exception e) {
			return PAGE_LIMIT;
		}
	}

	/**
	 * 获取paramMap中的limit参数<br>
	 * 若不存在，返回Long.MAX_VALUE
	 * 
	 * @return
	 */
	public long getLimitMax() {
		try {
			String limitName = "limit";
			if (paramMap.containsKey(limitName)) {
				String value = paramMap.get(limitName);
				if (value != null && value.trim().length() > 0) {
					return Long.parseLong(value);
				} else {
					return Long.MAX_VALUE;
				}
			} else {
				return Long.MAX_VALUE;
			}
		} catch (Exception e) {
			return Long.MAX_VALUE;
		}
	}
}
