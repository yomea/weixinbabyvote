package com.booway.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.booway.utils.RestObj;

/**
 * 全局异常处理
 * @author wuzhenhong
 *
 */
@Component
public class WeixinBabyExceptionResolver implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory
			.getLogger(WeixinBabyExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView mav = new ModelAndView();
		try {
			
			RestObj restObj = new RestObj();
			if (exception instanceof RestClientException) {
				restObj.setSuccessful(false);
				restObj.setData("");
				restObj.setMsg(exception.getMessage());
			} else {
				restObj.setSuccessful(false);
				restObj.setData("");
				restObj.setMsg("未知错误");
			}
			HandlerMethod hm = (HandlerMethod) handler;
			ResponseBody responseBody = hm.getMethodAnnotation(ResponseBody.class);
			RestController restController = hm.getBeanType().getAnnotation(RestController.class);
			
			if(responseBody != null || restController != null) {
				MappingJackson2HttpMessageConverter mjhmc = new MappingJackson2HttpMessageConverter();
				ServletServerHttpResponse sshr = new ServletServerHttpResponse(
						response);
				mjhmc.write(restObj, MediaType.APPLICATION_JSON_UTF8, sshr);
			} else {
				mav.setViewName("error");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return mav;
	}

}
