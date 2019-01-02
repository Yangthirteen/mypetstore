package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    protected static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Order order;

    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService=new OrderService();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart= (Cart) session.getAttribute("cart");

        if (account == null) {
            session.setAttribute("message","You must sign on before attempting to check out.  Please sign on and try checking out again.");
            request.getRequestDispatcher(NEW_ORDER).forward(request,response);
        } else if (cart != null) {
            order=new Order();
            order.initOrder(account, cart);
            session.setAttribute("order",order);
            request.getRequestDispatcher(NEW_ORDER).forward(request,response);
        } else {
            session.setAttribute("message","An order could not be created because a cart could not be found.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
