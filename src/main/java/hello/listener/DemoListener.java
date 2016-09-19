package hello.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("==>DemoListener启动");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
