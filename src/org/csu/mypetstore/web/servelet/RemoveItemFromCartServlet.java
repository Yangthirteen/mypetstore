package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR= "/WEB-INF/jsp/common/Error.jsp";

    private String cartItemId;

    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartItemId=request.getParameter("cartItemId");

        HttpSession session=request.getSession();
        cart=(Cart) session.getAttribute("cart");

        Item item=cart.removeItemById(cartItemId);
        if (item==null){
            session.setAttribute("message","Attempted to remove null CartItem from Cart.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }else {
            request.getRequestDispatcher(VIEW_CART).forward(request,response);
        }
    }
}
