package com.booway.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限拦截器
 * @author wuzhenhong
 *
 */
public class AuthorizeIntercepter implements HandlerInterceptor {
	
	@Value("${allowUrl}")
	private String allowUrl;
	
	@Value("${redirect_uri}")
	private String redirectUri;
	
	@Value("${codeUrl}")
	private String codeUrl;
	
	@Value("${appid}")
	private String appId;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
		//调试时注释到的，调试结束，需要打开
		/*HttpSession session = request.getSession(true);
		Authorize authorize = (Authorize) session.getAttribute("authorize");
		
		if(authorize != null) {
			return true;
		}
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		url.replace(url.indexOf(uri), url.length(), "");
		String contextPath = request.getContextPath();
		String redirect = url.append(contextPath).append(redirectUri).toString(); 
		String weixinCodeUrl = MessageFormat.format(codeUrl, appId, URLEncoder.encode(redirect, "utf-8"));
		
		String requestType = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(requestType)){
			RestObj restObj = new RestObj(false, "未授权", "0001",weixinCodeUrl);
			String jsonStr = GsonUtils.toJson(restObj);
			PrintWriter pw = response.getWriter();
			pw.write(jsonStr);
			pw.flush();
		} else {
			response.sendRedirect(weixinCodeUrl);
		}
		
		return false;*/
	}
}
