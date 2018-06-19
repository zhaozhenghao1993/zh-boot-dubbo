package net.zhenghao.zh.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * http上下文
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月30日 下午2:42:08
 * HttpContextUtils.java
 */
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
