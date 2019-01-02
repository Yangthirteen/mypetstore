package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditAccountFormServlet extends HttpServlet {

    private static final String EDITACCOUNTFORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private String username;
    private AccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService=new AccountService();
        username=request.getParameter("username");
        Account account=accountService.getAccount(username);

        HttpSession session=request.getSession();

        session.setAttribute("username",username);
        session.setAttribute("account",account);

        request.getRequestDispatcher(EDITACCOUNTFORM).forward(request,response);
    }
}
