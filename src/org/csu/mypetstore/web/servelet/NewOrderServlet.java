package org.csu.mypetstore.web.servelet;

import net.sourceforge.stripes.action.ForwardResolution;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {

    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    protected static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private OrderService orderService;

    private Order order;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        orderService = new OrderService();
        order = (Order) session.getAttribute("order");
        session.setAttribute("order", order);

        String a=request.getParameter("shippingAddressRequired");
        if (a!=null){
            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        }else{
            request.getRequestDispatcher(CONFIRM_ORDER).forward(request, response);
        }

    }
}


