package com.ninemax.mapper;

import com.ninemax.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Pual on 2016/8/26.
 */
public interface UserMapper {

    @Select("<script>select * from user</script>")
    List<User> findAll();

    @Select("<script>" +
            "SELECT " +
            "id, loginId as loginId, password, name, address, enabled, createTime, updateTime, role " +
            "FROM  " +
            "user " +
            "WHERE " +
            "<if test = \"loginId != null\">" +
            "loginId = #{loginId}" +
            "</if>" +
            "</script>")
    User findUserByLoginId(@Param("loginId") String loginId);

    @Insert("<script>" +
            "INSERT INTO user " +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"id != null\">" +
            "id," +
            "</if>" +
            "<if test=\"loginId != null\">" +
            "loginId," +
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
            "<if test =\"loginId != null\" >" +
            "#{loginId}, " +
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
    int insertUser(User user);

    @Update("<script>" +
            "UPDATE user" +
            "<set>" +
            "<if test=\"id != null\">" +
            "id = #{id}, " +
            "</if>" +
            "<if test=\"loginId != null\">" +
            "loginId = #{loginId}, " +
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
    int updateUser(User user);

    @Delete("<script>" +
            "DELETE " +
            "FROM " +
            "user " +
            "WHERE " +
            "<if test=\"id != null\">" +
            "id = #{id}" +
            "</if>" +
            "</script>")
    int deleteUserById(@Param("id") String id);


}
