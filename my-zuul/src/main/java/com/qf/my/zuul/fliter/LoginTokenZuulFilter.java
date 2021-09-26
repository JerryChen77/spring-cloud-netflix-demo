package com.qf.my.zuul.fliter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Cjl
 * @date 2021/8/18 20:59
 */
@Component
public class LoginTokenZuulFilter extends ZuulFilter {

    /**
     * 指明过滤器的类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指明相同类型的多个过滤器的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 2;
    }
    /**
     * 是否执行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext Context = RequestContext.getCurrentContext();
        return Context.sendZuulResponse();
    }
    /**
     * 执行具体的业务：用zuul网关去实现登陆验证：看这一次请求中cookie中有没有携带login_tokn，如果有的话放行，如果没有的话就返回“请登录！”
     *  重点关注：放行和不放行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("进入过滤器2");
        //去获得一个zuul的上下文对象
        RequestContext Context = RequestContext.getCurrentContext();
        Cookie[] cookies = Context.getRequest().getCookies();
        if (Objects.nonNull(cookies)){
            for (Cookie cookie : cookies) {
                if ("login_token".equals(cookie.getName())){
                    if ("login".equals(cookie.getValue())){
                        Context.setSendZuulResponse(true);
                        Context.setResponseStatusCode(200);
                        return null;
                    }
                }
            }
        }
        //不放行
        Context.setSendZuulResponse(false);
        Context.setResponseStatusCode(200);
        //重中之重，这里一定要加要给Response设置CharacterEncoding编码为UTF-8
        Context.getResponse().setCharacterEncoding("UTF-8");
        Context.setResponseBody("请先登录!!!");
        return null;
    }
}
