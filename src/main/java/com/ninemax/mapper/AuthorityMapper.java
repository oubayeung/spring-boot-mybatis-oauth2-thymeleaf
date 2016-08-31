package com.ninemax.mapper;

import com.ninemax.entity.Authority;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Pual on 2016/8/27.
 */
public interface AuthorityMapper {


    @Select("<script>select * from authority</script>")
    List<Authority> findAll();

    @Select("<script>" +
            "SELECT " +
            "authority " +
            "FROM " +
            "authority " +
            "WHERE " +
            "<if test=\"userId != null\">" +
            "userId = #{userId}" +
            "</if>" +
            "</script>")
    Authority findAuthorityByLoginId(@Param("userId") String userId);

    @Insert("<script>" +
            "INSERT INTO AUTHORITY" +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"userId != null\">" +
            "userId, " +
            "</if>" +
            "<if test=\"authority != null\">" +
            "authority, " +
            "</if>" +
            "</trim>" +
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"userId != null\">" +
            "#{userId}, " +
            "</if>" +
            "<if test=\"authority != null\">" +
            "#{authority}, " +
            "</if>" +
            "</trim>" +
            "</script>")
    int insertAuthority(Authority authority);

    @Update("<script>" +
            "UPDATE authority" +
            "<set>" +
            "<if test=\"userId != null\">" +
            "userId = #{userId}, " +
            "</if>" +
            "<if test=\"authority != null\">" +
            "authority = #{authority}, " +
            "</if>" +
            "</set>" +
            "WHERE" +
            "<if test=\"userId != null\">" +
            "userId = #{userId}, " +
            "</if>" +
            "</script>")
    int updateAuthority(@Param("userId") String userId);

    @Delete("<script>" +
            "DELETE " +
            "FROM " +
            "authority " +
            "WHERE " +
            "<if test=\"userId != null\">" +
            "userId = #{userId}" +
            "</if>" +
            "</script>")
    int deleteAuthority(@Param("userId") String userId);
}
