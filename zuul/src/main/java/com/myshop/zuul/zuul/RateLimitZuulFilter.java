package com.myshop.zuul.zuul;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URL;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * 通过zuul实现限流（主要是算法）
 *
 * 现在不起作用例子
 */
@Component
public class RateLimitZuulFilter extends ZuulFilter {
    private Map<String, RateLimiter> map = Maps.newConcurrentMap();

    //过滤类型有三种:pre  route  post
    @Override
    public String filterType() {
        return "pre";
    }

    //执行顺序(数字越小，优先级越高)
    @Override
    public int filterOrder() {
        return 6;
    }

    //是否开启过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤的具体内容
    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletRequest request = currentContext.getRequest();
            HttpServletResponse response = currentContext.getResponse();
            String key = null;
            // 对于service格式的路由，走RibbonRoutingFilter
            String serviceId = (String) currentContext.get(SERVICE_ID_KEY);
            if (serviceId != null) {
                key = serviceId;
                //每秒放2个
                map.putIfAbsent(serviceId, RateLimiter.create(2));
            }
            // 如果压根不走RibbonRoutingFilter，则认为是URL格式的路由
            else {
                URL routeHost = currentContext.getRouteHost();
                if (routeHost != null) {
                    String url = routeHost.toString();
                    key = url;
                    map.putIfAbsent(url, RateLimiter.create(2));

                }

            }
            RateLimiter rateLimiter = map.get(key);
            if (!rateLimiter.tryAcquire()) {
                HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
                response.setStatus(status.value());
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.getWriter().append(status.getReasonPhrase());
                currentContext.setSendZuulResponse(false);
                throw new ZuulException(
                        status.getReasonPhrase(),
                        status.value(),
                        status.getReasonPhrase()
                );

            }

        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
