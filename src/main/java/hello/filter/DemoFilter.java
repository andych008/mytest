package hello.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class DemoFilter implements Filter {


    private static final Logger logger = LoggerFactory.getLogger(DemoFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("==>DemoFilter启动");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        logger.info("==>DemoFilter拦截请求");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
