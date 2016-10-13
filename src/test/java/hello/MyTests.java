package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;


public class MyTests {


    private DecimalFormat df;


    private static final Logger logger = LoggerFactory.getLogger(MyTests.class);

    @Before
    public void setUp() throws Exception {

        logger.info("测试开始的准备");
        df = new DecimalFormat("##%");
    }


    @After
    public void tearDown() throws Exception {
        logger.info("测试结束前的操作");
        df=null;
    }

    @Test
    public void testAdd() {

        long a = 999;
//        long a = 1000;
        long total = 1000;
        logger.info("{}/{}={}", a, total, 999 / 1000);

        String result = df.format(Math.floor(Double.valueOf(a)*100 / Double.valueOf(total))/100);
        logger.info("ds: {}/{}={}", a, total, result);
        String expected = "99%";
        assertEquals(expected, result);
    }


}