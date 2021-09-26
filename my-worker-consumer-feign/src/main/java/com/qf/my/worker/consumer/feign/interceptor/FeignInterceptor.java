package com.qf.my.worker.consumer.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Cjl
 * @date 2021/8/18 11:26
 */
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //1.先获得cookie，在request对象里，-》先获得request对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        Cookie[] cookies = request.getCookies();
        String loginToken = "";
        for (Cookie cookie : cookies) {
            if ("login_token".equals(cookie.getName())){
                loginToken = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isNotBlank(loginToken)){
            requestTemplate.header(HttpHeaders.COOKIE,new StringBuffer().append("login_token").append("=").append(loginToken).toString());
        }
    }
}
