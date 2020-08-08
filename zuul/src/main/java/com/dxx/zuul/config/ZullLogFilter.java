package com.dxx.zuul.config;

import com.dxx.common.contants.DxxContants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cp5
 * @date 2020年 08月08日 13:01:28
 */
public class ZullLogFilter extends ZuulFilter {

    private Logger logger= LoggerFactory.getLogger(ZullLogFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token=request.getHeader(DxxContants.HEADER_TOKEN);

        logger.info("token="+token);
        logger.info("traceId="+ TraceContext.traceId());

        ctx.setSendZuulResponse(true); //对请求进行路由
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
