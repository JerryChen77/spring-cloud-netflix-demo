package com.qf.my.zuul.fliter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Cjl
 * @date 2021/8/18 20:59
 */
@Component
public class RequestLimitZuulFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;

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
        return 1;
    }
    /**
     * 是否执行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * 执行具体的业务：做一个计数器限流，限制后端服务的访问数5个，访问数谁来维护
     *  重点关注：放行和不放行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("进入过滤器1");
        //去获得一个zuul的上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //原子操作，防止线程安全问题
        Long count = redisTemplate.opsForValue().increment("zuul:count");
        if (count==1){
            redisTemplate.expire("zuul:count",60, TimeUnit.SECONDS);
        }
        //放行
        if (count<=5&&count>0){
            currentContext.setSendZuulResponse(true);
            currentContext.setResponseStatusCode(200);
            return null;
        }
        //不放行
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(200);
        //重中之重，这里一定要加要给Response设置CharacterEncoding编码为UTF-8
        currentContext.getResponse().setCharacterEncoding("UTF-8");
        currentContext.setResponseBody("Request Reach The Max Volume/限流中,请稍后");
        return null;
    }
}
