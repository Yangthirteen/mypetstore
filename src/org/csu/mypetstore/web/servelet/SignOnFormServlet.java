package org.csu.mypetstore.web.servelet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOnFormServlet extends HttpServlet {

    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(SIGNONFORM).forward(request,response);
    }
}
