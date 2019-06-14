package com.jokerliu.config.shiro;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
/**
 * @author JokerLiu
 * @create 2018-12-29 10:36
 * @desc 修改获取token的方式sessionId
 **/

public class ShiroSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public ShiroSessionManager() {
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头，或者请求参数中的Token
        String id = StrUtil.isEmpty(WebUtils.toHttp(request).getHeader(AUTHORIZATION))
                ? request.getParameter(AUTHORIZATION) : WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 如果请求头中有 Token 则其值为sessionId
        if (StrUtil.isNotEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return id;
        } else {
            // 否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
