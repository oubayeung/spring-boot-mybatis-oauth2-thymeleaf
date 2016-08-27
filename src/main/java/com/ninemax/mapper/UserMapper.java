package com.ninemax.mapper;

import com.ninemax.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Pual on 2016/8/26.
 */
public interface UserMapper {

    @Select("<script>" +
            "SELECT " +
            "id, login_id as loginId, password, name, address, enabled, createTime, updateTime, role " +
            "FROM  " +
            "user " +
            "WHERE " +
            "<if test = \"loginId != null\">" +
            "login_id = #{loginId}" +
            "</if>" +
            "</script>")
    public User findUserByLoginId(@Param("loginId") String loginId);

    @Insert("<script>" +
            "INSERT INTO user " +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"id != null\">" +
            "id," +
            "</if>" +
            "<if test=\"login_id != null\">" +
            "login_id," +
            "</if>" +
            "<if test=\"password != null\">" +
            "password," +
            "</if>" +
            "<if test=\"name\">" +
            "name," +
            "</if>" +
            "<if test=\"address != null\">" +
            "address," +
            "</if>" +
            "<if test=\"enabled != null\">" +
            "enabled," +
            "</if>" +
            "<if test=\"createTime != null\">" +
            "createTime," +
            "</if>" +
            "<if test=\"updateTime != null\">" +
            "updateTime," +
            "</if>" +
            "<if test=\"role != null\">" +
            "role," +
            "</if>" +
            "</trim>" +
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test =\"id != null\">" +
            "#{id}, " +
            "</if>" +
            "<if test =\"login_id != null\" >" +
            "#{login_id}, " +
            "</if>" +
            "<if test=\"password != null\">" +
            "#{password}, " +
            "</if>" +
            "<if test=\"name != null\">" +
            "#{name}, " +
            "</if>" +
            "<if test=\"address != null\">" +
            "#{address}, " +
            "</if>" +
            "<if test=\"enabled != null\">" +
            "#{enabled}, " +
            "</if>" +
            "<if test=\"createTime != null\">" +
            "#{createTime}, " +
            "</if>" +
            "<if test=\"updateTime != null\">" +
            "#{updateTime}, " +
            "</if>" +
            "<if test=\"role != null\">" +
            "#{role}, " +
            "</if>" +
            "</trim>" +
            "</script>")
    public int insertUser(User user);

    @Update("<script>" +
            "UPDATE user" +
            "<set>" +
            "<if test=\"id != null\">" +
            "id = #{id}, " +
            "</if>" +
            "<if test=\"login_id != null\">" +
            "login_id = #{login_id}, " +
            "</if>" +
            "<if test=\"password != null\">" +
            "password = #{password}, " +
            "</if>" +
            "<if test=\"name\">" +
            "name = #{name}, " +
            "</if>" +
            "<if test=\"address != null\">" +
            "address = #{address}, " +
            "</if>" +
            "<if test=\"enabled != null\">" +
            "enabled = #{enabled}, " +
            "</if>" +
            "<if test=\"createTime != null\">" +
            "createTime = #{createTime}, " +
            "</if>" +
            "<if test=\"updateTime != null\">" +
            "updateTime = #{updateTime}, " +
            "</if>" +
            "<if test=\"role != null\">" +
            "role = #{role}, " +
            "</if>" +
            "</set>" +
            "WHERE" +
            "<if test=\"id != null\">" +
            "id = #{id}" +
            "</if>" +
            "</script>")
    public int updateUser(@Param("id") String id);

    @Delete("<script>" +
            "DELETE " +
            "FROM " +
            "user " +
            "WHERE " +
            "<if test=\"id != null\">" +
            "id = #{id}" +
            "</if>" +
            "</script>")
    public int deleteUser(@Param("id") String id);
}
