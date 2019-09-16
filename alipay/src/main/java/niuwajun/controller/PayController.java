package niuwajun.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import niuwajun.commmon.model.BusinessException;
import niuwajun.commmon.model.RequestParamMap;
import niuwajun.service.PayService;

@RestController
public class PayController extends BaseController{

	@Resource
	private PayService payService;
	
	@RequestMapping("/alipay/orderAliPrepay4App.do")
	public String orderAliPrepay4App(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 封装请求参数
			RequestParamMap paramMap = new RequestParamMap(request);

			JSONObject result = payService.orderAliPrepay4App(paramMap.getMap());

			return returnSuccess("预支付成功", result);
		} catch (BusinessException e) {
			// 业务错误，报错信息提供给接口调用者，不进行日志记录
			return returnError(e.getMessage());
		} catch (Exception e) {
			// 系统错误，进行日志记录
			log.error(e);
			return returnError("支付失败");
		}
	}
	
	
}
