package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewOrderListServlet extends HttpServlet {

    private  static final String VIEW_ORDER_LIST="/WEB-INF/jsp/order/ListOrders.jsp";

    private OrderService orderService;
    private String username;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService=new OrderService();
        HttpSession session=request.getSession();
        username=(String) session.getAttribute("username");
        List<Order> orderList=orderService.getOrdersByUsername(username);

        session.setAttribute("orderList",orderList);

        request.getRequestDispatcher(VIEW_ORDER_LIST).forward(request,response);

    }
}
