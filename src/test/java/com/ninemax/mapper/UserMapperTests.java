package com.ninemax.mapper;

import com.ninemax.Application;
import com.ninemax.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Pual on 2016/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAuthorityByLoginId() {
        User a = userMapper.findUserByLoginId("f041e5a");

        System.out.println("=========\n==========\n=========\n==========\n");
        System.out.println(a);
    }


    @Test
    public void testdeleteAuthorityByLoginId() {

        int a = userMapper.deleteUser("f041e5a");

        System.out.println("=========\n==========\n=========\n==========\n");
        System.out.println(a);
    }

}
