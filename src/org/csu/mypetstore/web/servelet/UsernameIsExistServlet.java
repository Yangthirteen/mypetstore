package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            Account account = new Account();
            account.setUsername(username);
            AccountService service = new AccountService();
            System.out.println(username);
            System.out.println("   1");
            response.setContentType("text/xml");
            PrintWriter out = response.getWriter();

            if(service.usernameIsExist(account)){
                out.println("<msg>Exist</msg>");
            }
            else {
                out.println("<msg>NotExist</msg>");
            }
            out.flush();
            out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }


}
