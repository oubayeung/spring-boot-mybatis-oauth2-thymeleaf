package com.ninemax.mapper;

import com.ninemax.entity.Authority;
import com.ninemax.entity.User;

/**
 * Created by Pual on 2016/8/26.
 */
public interface UserMapper {

    public User findUserByLoginId(String loginId);

    public Authority findAuthorityByLoginId(String loginId);
}
