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


    protected void showMemorialsDetail (HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        //1.从请求参数中读取memorialsId
        String memorialsId = request.getParameter("memorialsId");

        //2,   根据memorialsId 从service中 memorials 对象
        Memorials memorials =  memorialsService.getMemorialsDetailById(memorialsId);

        //3,将memorials 对象存入请求域。
        request.setAttribute("memorials",memorials);

        //4.渲染视图。
        String templatetName = "memorials_detail";

        processTemplate(templatetName,request,response);


    }
}
