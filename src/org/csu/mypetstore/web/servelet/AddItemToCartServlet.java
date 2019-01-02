package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserActionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItemToCartServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;

    private CatelogService catelogService;
    private UserActionService userActionService;

    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId=request.getParameter("workingItemId");

        HttpSession session=request.getSession();
        cart=(Cart) session.getAttribute("cart");

        if(cart==null){
            cart=new Cart();
        }

        if (cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }else {
            catelogService=new CatelogService();
            boolean isInStock = catelogService.isItemInStock(workingItemId);
            Item item = catelogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }


        Date currentData=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String date = sdf.format(currentData);
        userActionService=new UserActionService();
        Account account=(Account)session.getAttribute("account");
        userActionService.record(account.getUsername(),"add item to cart ",workingItemId,date);


        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
