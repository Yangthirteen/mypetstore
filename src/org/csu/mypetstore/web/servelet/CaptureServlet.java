package org.csu.mypetstore.web.servelet;

import org.csu.mypetstore.persistence.CaptchaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 通知浏览器不要缓存
            response.setHeader("Expires", "-1");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "-1");
            CaptchaUtil util = CaptchaUtil.Instance();
            // 将验证码输入到session中，用来验证
            String code = util.getString();
            request.getSession().setAttribute("code", code);
            // 输出打web页面
            ImageIO.write(util.getImage(), "jpg", response.getOutputStream());

        }
    }


