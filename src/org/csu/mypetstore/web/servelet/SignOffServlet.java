package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOffServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catelog/Main.jsp";

    private AccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
       // boolean flag=true;
       // session.setAttribute("flag",flag);
        session.setAttribute("account",null);
        request.getRequestDispatcher(MAIN).forward(request,response);
    }
}
