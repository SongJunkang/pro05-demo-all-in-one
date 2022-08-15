package com.atguigu.imperial.court.servlet.module;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.exception.LoginFailedException;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.service.impl.EmpServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;
import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends ModelBaseServlet {

    private EmpService empService = new EmpServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //1获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");

            //2。调用Empservice方法，执行d登录操作
            Emp emp = empService.getEmpByLoginAccount(loginAccount,loginPassword);


            //3，通过request获取Httpsession 对象。
            HttpSession session = request.getSession();

            //4，查询到的Emp对象存入，Session域
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME,emp);


            //5前往页面指定视图。
            //此为临时页面。
//            String templateName = "temp";
//            processTemplate(templateName,request,response);
            //前往正式的地址。
            response.sendRedirect(request.getContextPath()+ "/work?method=showMemorialsDigestList");


        } catch (Exception e) {

            //6 此处捕获的异常是否是登录失败异常
            if (e instanceof LoginFailedException)
            {
                //7 ，如果是登录异常，则跳转登录页面。

                //一，将异常信息存入请求域
                request.setAttribute("message",e.getMessage());

                //二，处理视图index
                processTemplate("index",request,response);
            }else {
                //8，如果不是登录异常，则封装为运行时异常
                throw new RuntimeException(e);


            }

        }

    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,通过request 对象h获取HttpSession对象。
        HttpSession session = request.getSession();

        //2，将HttpSession 对象强制失效。
        session.invalidate();

        //3.还是回到首页 。
        String templatementName = "index";
        processTemplate(templatementName,request,response);

    }
}
