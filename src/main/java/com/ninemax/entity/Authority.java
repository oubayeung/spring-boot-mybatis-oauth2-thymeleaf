package com.ninemax.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Pual on 2016/8/26.
 */

public class Authority implements GrantedAuthority{

    private String userId;
    private String authority;
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userId='" + userId + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
