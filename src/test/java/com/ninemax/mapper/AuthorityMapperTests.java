package com.ninemax.mapper;

import com.ninemax.Application;
import com.ninemax.entity.Authority;
import com.ninemax.utils.Tools;
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
public class AuthorityMapperTests {
    @Autowired
    private AuthorityMapper authorityMapper;


    @Test
    public void testInsertAuthority() {
        Authority authority = new Authority();
        authority.setUserId(Tools.getLowerCaseUUID8NoBlank());
        authority.setAuthority("ROLE_ADMIN,ROLE_USER");
        int i = authorityMapper.insertAuthority(authority);
        if (i != -1) {
            System.out.println("\n新增Authority成功!\n\n");
            String userId = authority.getUserId();
            Authority a = authorityMapper.findAuthorityByLoginId(userId);
            System.out.println(a+ "\n");
        } else {
            System.out.println("新增Authority失败!");
        }
    }
    @Test
    public void testFindAuthorityByLoginId() {
        Authority a = authorityMapper.findAuthorityByLoginId("f041e5a");
        System.out.println("=========\n==========\n=========\n==========\n");
        System.out.println(a);
    }


    @Test
    public void testdeleteAuthorityByLoginId() {

        int a = authorityMapper.deleteAuthority("f041e5a1e0c44c60ad65138177cc79fb");

        System.out.println("=========\n==========\n=========\n==========\n");
        System.out.println(a);
    }

}
