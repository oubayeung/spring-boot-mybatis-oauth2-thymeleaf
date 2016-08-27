package com.ninemax.service;


import com.ninemax.entity.Authority;
import com.ninemax.entity.User;
import com.ninemax.mapper.AuthorityMapper;
import com.ninemax.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Pual on 2016/8/26.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userMapper.findUserByLoginId(loginId);
        if (user == null) {
            logger.error(loginId + "记录为空!");
            throw new UsernameNotFoundException(loginId);
        }
        Authority authority = authorityMapper.findAuthorityByLoginId(user.getId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public int deleteUserById(String userId) throws Exception{
        return userMapper.deleteUser(userId);
    }
}
