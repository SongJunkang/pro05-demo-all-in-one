package com.atguigu.imperial.court.filter;

import com.atguigu.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogRecord;

/**
 * @author joakims
 * @create 2022-08-10-12:11
 **/
public class TransactionFilter implements Filter {

    //声明集合，保存静态资源拓展名。
    private  static Set<String> staticResourceExtNameSet;
    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jbg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");


    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //前置操作：排除静态资源
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();

        if (servletPath.contains("."))
        {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));
            if (staticResourceExtNameSet.contains(extName))
            {
                //如果检车到当前请求是静态资源，则直接放行，不做事务操作。

                filterChain.doFilter(servletRequest,servletResponse);
                //当前方法直接返回，不做执行。
                return;
            }

        }


        Connection connection = null;
        try {
            //1、获取数据库链接
            connection = JDBCUtils.getConnection();

            //重要操作，关闭自动提交功能
            connection.setAutoCommit(false);


            //2、核心操作
            filterChain.doFilter(servletRequest, servletResponse);

            //3、提交事务。
            connection.commit();

        } catch (Exception e) {
            try {
                //4、回滚事务
                assert connection != null;
                connection.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            //页面显示：将这里捕获到的异常发送到指定页面显示。
            //获取异常信息

            String message = e.getMessage();
            //将异常信息存入请求域

            request.setAttribute("systemMessage",message);

            //将请求转发到指定页面。
            request.getRequestDispatcher("/").forward(request,servletResponse);


        } finally {
            //5、释放数据库链接

            JDBCUtils.releaseConnection(connection);

        }
    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
