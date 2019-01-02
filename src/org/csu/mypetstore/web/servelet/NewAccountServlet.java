package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {

    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;
    private AccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        account=new Account();
        accountService=new AccountService();

        account.setUsername(request.getParameter("username"));
        account.setPassword(request.getParameter("password"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setEmail(request.getParameter("account.email"));
        account.setPhone(request.getParameter("account.phone"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));

        accountService.insertAccount(account);

        request.getRequestDispatcher(SIGNONFORM).forward(request,response);

    }
}
