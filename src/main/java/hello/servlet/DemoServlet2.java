package hello.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/demoservlet2", description="Servlet的说明") // 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.MyServlet2
public class DemoServlet2 extends HttpServlet {

    private static final long serialVersionUID = 2600143224322327782L;

    private static final Logger logger = LoggerFactory.getLogger(DemoServlet2.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("==>DemoServlet接受请求22222222");
        resp.getWriter().write("DemoServlet access success 22222222222");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

}