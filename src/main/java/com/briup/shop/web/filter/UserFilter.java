package com.briup.shop.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adam
 * @date 2022/1/13
 */
@Component
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user==null){
        httpServletRequest.setAttribute("msg","还未登录请先登录");
        httpServletRequest.getRequestDispatcher("toLogin").forward(httpServletRequest,httpServletResponse);
        }else {
            chain.doFilter(request,response);
        }

    }
}
