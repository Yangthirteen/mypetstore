package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {

    private  static final String SEARCH_PRODUCTS="/WEB-INF/jsp/catelog/SearchProducts.jsp";

    private String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword=request.getParameter("keyword");
        CatelogService catelogService=new CatelogService();
        List<Product> productList=catelogService.searchProductList(keyword);

        HttpSession session=request.getSession();
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(SEARCH_PRODUCTS).forward(request,response);
    }
}
