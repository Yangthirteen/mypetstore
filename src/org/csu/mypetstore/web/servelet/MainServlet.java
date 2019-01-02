package org.csu.mypetstore.web.servelet;


import java.io.IOException;


public class MainServlet extends javax.servlet.http.HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catelog/Main.jsp";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher(MAIN).forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}

