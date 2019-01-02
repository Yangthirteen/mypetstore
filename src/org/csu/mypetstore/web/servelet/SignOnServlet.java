package org.csu.mypetstore.web.servelet;

import com.mysql.jdbc.StringUtils;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {

    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catelog/Main.jsp";

    private String username;
    private String password;
    private AccountService accountService;
    private boolean authenticated;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService=new AccountService();
        username=request.getParameter("username");
        password=request.getParameter("password");
        Account account=accountService.getAccount(username,password);
        HttpSession session=request.getSession();
        String codeSession = request.getParameter("code1");
        String code=(String)session.getAttribute("code");
        boolean flag;
        if (!code.toLowerCase().equals(codeSession.toLowerCase())){
            flag=true;
            session.setAttribute("flag",flag);
            request.getRequestDispatcher(SIGNONFORM).forward(request,response);
        }else{
            flag=false;
            if (account==null){
                authenticated=false;
                session.setAttribute("message","Invalid username or password.  Signon failed.");
                request.getRequestDispatcher(SIGNONFORM).forward(request,response);
            }else{
                authenticated=true;
                session.setAttribute("authenticated",authenticated);
                session.setAttribute("account",account);
                session.setAttribute("flag",flag);
                request.getRequestDispatcher(MAIN).forward(request,response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
