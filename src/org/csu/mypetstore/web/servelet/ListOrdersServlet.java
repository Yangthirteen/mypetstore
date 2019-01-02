package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListOrdersServlet extends HttpServlet {

    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";

    List<Order> orderList;

    OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService=new OrderService();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        orderList = orderService.getOrdersByUsername(account.getUsername());

        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(LIST_ORDERS).forward(request,response);
    }
}
