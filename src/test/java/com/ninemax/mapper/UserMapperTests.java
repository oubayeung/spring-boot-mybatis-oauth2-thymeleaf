package com.ninemax.mapper;

import com.ninemax.Application;
import com.ninemax.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

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
    public void testFindUserByLoginId() {
        User a = userMapper.findUserByLoginId("pual");

        System.out.println("=========\n==========\n=========\n==========\n");
        System.out.println(a);
    }


    @Test
    public void testDeleteUserById() {

        int a = userMapper.deleteUserById("3");
        if (a > 0) {
            System.out.println("删除成功！");
            List<User> all = userMapper.findAll();
            if (all.size() != 0) {
                for (User user : all) {
                    System.out.println(user);
                }
            } else {
                System.err.println("查询记录为空！");
            }

        }
    }


    @Test
    public void testUpdateUser() {
        User user = userMapper.findUserByLoginId("pual");
        if (user != null) {
            user.setName("张启山"); // ROLE_ADMIN,ROLE_USER,ROLE_GUEST
            //user.setRole(user.getRole() + ",ROLE_GUEST");
            user.setUpdateTime(new Date());
            userMapper.updateUser(user);
        }
    }


    @Test
    public void testInsertUser() {
        User user = new User();
        user.setId("3");
        user.setLoginId("Ammy");
        user.setName("二月红");
        user.setPassword("123456");
        user.setAddress("北京市东城区");
        user.setCreateTime(new Date());
        user.setRole("ROLE_USER,ROLE_GUEST");
        user.setEnabled(false);
        userMapper.insertUser(user);
    }


}
