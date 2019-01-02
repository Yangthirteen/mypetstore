package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserActionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ViewItemServlet extends HttpServlet {

    private  static final String VIEW_PRODUCT="/WEB-INF/jsp/catelog/Item.jsp";

    private UserActionService userActionService;

    private String itemId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId=request.getParameter("itemId");
        CatelogService catelogService=new CatelogService();
        Product product=null;
        Item item=null;
        if (catelogService.isItemInStock(itemId)){
            item=catelogService.getItem(itemId);
            product=catelogService.getItem(itemId).getProduct();
        }

        HttpSession session=request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("item",item);

        Date currentData=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String date = sdf.format(currentData);
        userActionService=new UserActionService();
        Account account=(Account)session.getAttribute("account");
        userActionService.record(account.getUsername(),"browse item",itemId,date);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
