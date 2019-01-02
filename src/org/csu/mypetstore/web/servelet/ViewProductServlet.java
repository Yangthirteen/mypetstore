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

public class ViewProductServlet extends HttpServlet {

    private  static final String VIEW_PRODUCT="/WEB-INF/jsp/catelog/Product.jsp";

    private String productId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId=request.getParameter("productId");
        CatelogService catelogService=new CatelogService();
        Product product=catelogService.getProduct(productId);
        List<Item> itemList=catelogService.getItemListByProduct(productId);

        HttpSession session=request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
