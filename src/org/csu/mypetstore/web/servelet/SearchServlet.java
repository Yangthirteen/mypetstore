package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ajax处理页面禁用缓存
        resp.setHeader("Content-type", "text/html;charset=utf-8");
        resp.setHeader("expirs", "mon,26jul199705:00:00gmt");
        resp.setHeader("cache", "no-cache,must-revalidate");
        resp.setHeader("pragma", "no-cache");
        //是设置字符集
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        //获取传值，搜索商品名称的关键字
        String keyword = req.getParameter("keyword");
        System.out.println(keyword);
        //查询
        SearchService service = new SearchService();
        String search = "";
        try {
            search = service.searchMessage(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //search = "123#123#12";
        out.print(search);
        if(search==null) System.out.println("error");
        else System.out.println(search);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
