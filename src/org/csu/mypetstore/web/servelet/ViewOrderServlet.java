package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.service.UserActionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private OrderService orderService;
    private UserActionService userActionService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService=new OrderService();
        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("account");
        Order order=(Order) session.getAttribute("order");
        orderService.setOrderId(order);

        try{
            orderService.insertOrder(order);
        }catch (Exception e){
            e.printStackTrace();
        }

        Date currentData=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String date = sdf.format(currentData);
        userActionService=new UserActionService();
        userActionService.record(account.getUsername(),"new order","",date);

        session.setAttribute("order",order);
        session.setAttribute("account",account);
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);

    }
}
