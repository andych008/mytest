package hello;

import hello.mapper.UserMapper;
import hello.model.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 再次测试先删除旧的数据
 SET SQL_SAFE_UPDATES = 0;
 delete from mytest.UserInfo where name = 'AAA';
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findByName() throws Exception {
        userMapper.insert("AAA", 20);
        UserInfo u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
    }

}