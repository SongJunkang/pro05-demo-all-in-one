package com.atguigu.imperial.court.filter;

import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取httpsession 对象。
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();

        //2，尝试从session 域中获取已登录的对象。
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);

        //3.判断loginEmp 是否为空，
        if (loginEmp != null){
            //4若不为空，那么说明当前已经登录。直接放行就可以。

            chain.doFilter(request1,response);
            return;
        }
        //5 若为空，说明尚未登录，则回到登录页面，
        request1.setAttribute("systemMessage",ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        //重新回到登录页面。
        request1.getRequestDispatcher("/").forward(request1,response);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
