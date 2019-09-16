package niuwajun.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * App端相关Controller基础类
 */
public class BaseController {

	/**
	 * 本地日志记录对象
	 */
	public final Logger log = LogManager.getLogger(this.getClass());

	/**
	 * 从请求中获取所需参数，若参数为空，返回空字符串（不是null）
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public String getParam(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);
		if (paramValue != null && paramValue.trim().length() > 0) {
			return paramValue.trim();
		} else {
			return "";
		}
	}

	/**
	 * 返回单个对象
	 * 
	 * @param jsonArray 记录列表
	 * @param total 总记录数
	 * @return
	 */
	public String returnObject(String objectName, JSONObject jsonObject) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("message", "成功");
		jsonObj.put(objectName, jsonObject);

		return jsonObj.toJSONString();
	}

	/**
	 * 返回列表
	 * 
	 * @param jsonArray 记录列表
	 * @param total 总记录数
	 * @return
	 */
	public String returnRecords(JSONArray jsonArray, long total) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("message", "成功");
		jsonObj.put("result", jsonArray);
		jsonObj.put("total", total);

		return jsonObj.toJSONString();
	}

	/**
	 * 返回成功
	 * 
	 * @return
	 */
	public String returnSuccess() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("message", "成功");

		return jsonObj.toJSONString();
	}

	/**
	 * 返回成功
	 * 
	 * @param message 成功信息（供用户查看）
	 * @return
	 */
	public String returnSuccess(String message) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("message", message);

		return jsonObj.toJSONString();
	}

	/**
	 * 返回成功，包括附加信息
	 * 
	 * @param message 成功信息（供用户查看）
	 * @param paramJson 返回的附加信息
	 * @return
	 */
	public String returnSuccess(String message, JSONObject params) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("message", message);

		if (params != null) {
			for (String key : params.keySet()) {
				Object value = params.get(key);
				jsonObj.put(key, value);
			}
		}

		return jsonObj.toJSONString();
	}

	/**
	 * 返回失败，包括错误信息
	 * 
	 * @param message 错误信息
	 * @return
	 */
	public String returnError(String message) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", false);
		jsonObj.put("message", message);
		jsonObj.put("error", "");

		return jsonObj.toJSONString();
	}

	/**
	 * 返回失败，包括错误信息和错误描述
	 * 
	 * @param message 错误信息
	 * @param error 错误描述
	 * @return
	 */
	public String returnError(String message, String error) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", false);
		jsonObj.put("message", message);
		jsonObj.put("error", error);

		return jsonObj.toJSONString();
	}

	/**
	 * 返回失败，包括错误信息和错误描述和附加信息
	 * 
	 * @param message 错误信息
	 * @param error 错误描述
	 * @param paramJson 附加信息
	 * @return
	 */
	public String returnError(String message, String error, JSONObject params) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", false);
		jsonObj.put("message", message);
		jsonObj.put("error", error);

		if (params != null) {
			for (String key : params.keySet()) {
				Object value = params.get(key);
				jsonObj.put(key, value);
			}
		}

		return jsonObj.toJSONString();
	}
}
