package com.example.filecomparator.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

import java.util.UUID;

@Component
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    public static final String APP_REQ_ID = "apprequestid";
    public static final String APP_RES_ID = "appresponseid";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        String str = request.getHeader(APP_REQ_ID);
        if (str != null && str.length() > 0) {
            MDC.put(APP_REQ_ID, str);
        } else {
            MDC.put(APP_REQ_ID, UUID.randomUUID().toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        response.setHeader(APP_RES_ID, MDC.get(APP_REQ_ID));
        MDC.clear();
    }
}
