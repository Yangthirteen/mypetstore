package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {

    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private AccountService accountService;
    Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService=new AccountService();
        HttpSession session=request.getSession();
        account=(Account) session.getAttribute("account");
        accountService.updateAccount(account);
        session.setAttribute("account",account);
        request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
    }
}
