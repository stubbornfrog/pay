package niuwajun.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import niuwajun.commmon.model.BusinessException;

@Service
public class PayService {

	/**
	 * APP端支付宝预支付订单
	 * 
	 * @throws Exception
	 */
	public JSONObject orderAliPrepay4App(Map<String, String> paramMap) throws Exception {
		try {
			/* --------------- 获取参数 --------------- */
			String app_appId = "2016082000294451";
			String app_privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJo9IDGjr3Lxw43J+gkKPXRjQ/iA4xqpsz2q77sa+v+bNejXrdWxJnNzDv7vJHzEOm+vHZflpu2CYoxFJwd4x4tcGEAUOTbf0LHLUGQbV20sP0xsN4QZB5q28XuvAXEdiAwUOZIk0v+i6JiwAGGwfH9cHXxQ++KYK0v3cXhEm4suLZ8PU0pVchuKcd+Nwo3gt773cnyYPUZQT41ZgX5wX3zy3AypiYFodcQLXtGeJASQonZnF7lK8Ab6PoapaVQgU9c/oqRLuy0g3iCW7jbmuK17LXRkubS/+8I39c9BMcwCF4bmVcrRxHX0C0nIGG0UlbciAW3IpubpA3o7n1jY8PAgMBAAECggEARJIy7IzC16bGvqVbeVjV50f4e7ZCrAe87qaCCq7JQjh6Xh7KGUFn97gNT1uRpKdlBOl8V8iVMSfKSwETuSg1+WVDlYozql1fdYvFu+o8h4gVS/tCIsU3PwZjgEUHm9auL+cY0E/AeKiBStDPIDuTMtGC0wZAA5MWpQnGQRcMy1DImBgQ+IpjtoGrvnNqSVz1RMTX3vRFtdKv4sQIr0N85DL47XHW2H/1nRCqWGHU4PbxciPFEIiB51fivOvk9cmX6o7xq9I/QFcVeRZRaqBJ/o0SByIKBdffZTieGD4weE1eOKl0fyHsQZhL++9Te9xi9OFyKi5vIMOEXy8ysf1Y8QKBgQC+x1IrnnJRqcq9UgiTLsJvVHXcbIDEmoSSDhT5L0ZZjzHorfww1KIEZXsyWBr5ESkq+vnmp1OUth4gsBQE2NxxpVCtTYY0zLJ4xsHwOkwIqv8XjQg6PdvU3++YHnn1aNwRjCYvN1CQhX7S/5GZVg8LAE2v2mVRnfp5u5ZSNzTT+wKBgQC4seBH1V6JE3aHPNYLT7QHZuPXY5uiTwcnLgbMtEALBHU14/mzGDkL6voVOSnmSt3NceosPHICgLAONtvw7MkT6iEqb3n1ivLcQIY/gQX/doVhaHnOH69yuEK+SwEYtk6dGl9ZKHaQ96TjqVF6aS6EiU4wopQo31yKgZti33sw/QKBgD6FyDIvXTvSL3VYOyMxQduGjJHOpWzJTwUmxEzd52pWeX7N9kZyiR5zu6pGIaDatOk3E0s6LzQfc0Q3MHheMfC0wvno3EDVjO/0A38dO+njUHKBb97WgwMC2Ny7sDBzSxwdGC6FLEwQ0/2GcEsWXxu137/wDSBwa7QVfDA5k4CdAoGBAKFVwDT/jbTv63n3CoKW/Qv6NMzRlgISRo1TB12JlugI3NpWaqinYC2o9QvVA2THwTjVaVl685x8DU5dXYL3VC6xNod7GW4EEmGTe7G9Yx/kyF5uhPgpdXkgvCy+GBceKU3S27dmQSwhJ0g3f5UOjDP6PPL/Ea0icBQNY07bKSGBAoGADkUQL2HRjzhLnIWedMQXLTXun7EW/15RqMQ3IXH9oirY813hBSd4aQ4ZKfpRspGukEr6M+X/01CADujAHIEE8rzxJz96LCMWj6FdmmOC/6LlRyZctE/bt/cXpAwegfc4sp/ugPWfVNVU0PLg/tE3SOtGNvhRbHavUJf4PxMM2vg=";
			String app_publicKey = "M2IIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz41zGJp8woygAhi4Z198yZHv+xhvpbDfbpIDzVAgUJQIi/UgLNik2M8SAf3IIzcmMvBwDerI9xRH5Zl1X58J11QGrzhjKQPHu0bkC7wyIfN16pUDPxqoJubiqv4g244NEOGNWBs6pqpa9OxdD/HvL5PL81DREt19oHxrri8RNLJ/Rchkh0h3itM8mNiDpyRiuuMYEe4qMyjmfTHGXO9x7gKjtOslcEDTt+YaAULEBc/BCQpkP3Xi1EpZ6hjrvZY4RRVQuuLWbDyIQtXq68CBdZvhyy2hvvv22G74YLydwQS73JUhE8qznJabDuuQTPsRTbF+nBWb3aAdAMvOvFPgPwIDAQAB";
			
//			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//			map.put("app_id", app_appId);
//			map.put("method", "alipay.trade.app.pay");
//			map.put("charset", "utf-8");
//			map.put("sign_type", "RSA2");
//			map.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			map.put("version", "1.0");
//			map.put("notify_url", "http://cxyzmm.vicp.net:24246/doNotifiy.do");

			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("测试商品描述");
			model.setSubject("测试商品标题");
			model.setOutTradeNo(UUID.randomUUID().toString());
			model.setTimeoutExpress("30m");
			model.setProductCode("QUICK_MSECURITY_PAY");
			model.setTotalAmount("0.01");
			
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();

			alipayRequest.setBizModel(model);
			alipayRequest.setNotifyUrl("http://cxyzmm.vicp.net:24246/doNotifiy.do");

			String form="";
			//获得初始化的AlipayClient
			try {
				AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", app_appId, app_privateKey, "json", "utf-8", app_publicKey, "RSA2");
				AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alipayRequest);
				form=response.getBody();
			} catch (AlipayApiException e) {
				throw e;
			}
//			httpResponse.setContentType("text/html;charset=" + AlipayConfig.inputCharset);
//			httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//			httpResponse.getWriter().flush();
//			httpResponse.getWriter().close();
			
			JSONObject resultJsonObject = new JSONObject();
			resultJsonObject.put("payInfo", form);
			return resultJsonObject;
			
		} catch (BusinessException e) {
			// 业务错误，报错信息提供给接口调用者，不进行日志记录
			throw e;
		} catch (Exception e) {
			// 系统错误，进行日志记录
			throw e;
		}

	}
	
}
