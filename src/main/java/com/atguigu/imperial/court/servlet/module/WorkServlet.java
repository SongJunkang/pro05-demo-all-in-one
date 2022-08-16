package com.atguigu.imperial.court.servlet.module;

import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;
import com.atguigu.imperial.court.service.impl.MemorialsServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WorkServlet extends ModelBaseServlet {

    private MemorialsService memorialsService = new MemorialsServiceImpl();


    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service方法 查询数据。
        List<Memorials> memorialsList =memorialsService.getAllMemorialsDigest();

        //2.将查询得到的时间存入请求域。
        request.setAttribute("memorialsList",memorialsList);

        //3,渲染视图。
        String  templatementName = "memorials-list";
        processTemplate(templatementName,request,response);

    }


    protected void showMemorialsDetail(HttpServletRequest request,
                                        HttpServletResponse response)
            throws ServletException, IOException {
        //1.从请求参数中读取memorialsId
        String memorialsId = request.getParameter("memorialsId");

        //2,   根据memorialsId 从service中 memorials 对象
        Memorials memorials =  memorialsService.getMemorialsDetailById(memorialsId);


        // **********************补充功能**********************
        // 获取当前奏折对象的状态
        Integer memorialsStatus = memorials.getMemorialsStatus();

        //判断奏折状态
        if (memorialsStatus == 0)
        {
            //更新奏折状态。数据库修改，
            memorialsService.updateMemorialsStatusToRead(memorialsId);
            //更新奏折状态。当前对象修改。
            memorials.setMemorialsStatus(1);

        }


        //3,将memorials 对象存入请求域。
        request.setAttribute("memorials",memorials);

        //4.渲染视图。
        String templatetName = "memorials_detail";

        processTemplate(templatetName,request,response);
    }


    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取表单提交的请求参数
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");

        // 执行更新
        memorialsService.updateMemorialsFeedBack(memorialsId, feedbackContent);

        // 重定向回显示奏折列表页面
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
