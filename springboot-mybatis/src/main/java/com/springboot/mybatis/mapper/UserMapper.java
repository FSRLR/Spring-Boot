package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Results({@Result(column = "user_id",property = "userId"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "password",property = "password"),
            @Result(column = "username",property = "username"),
            @Result(column = "avatar",property = "avatar")
    })
    @Select("SELECT * FROM t_sys_user ")
    List<User> selectAll();


    @Results({@Result(column = "user_id",property = "userId"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "password",property = "password"),
            @Result(column = "username",property = "username"),
            @Result(column = "avatar",property = "avatar")
    })
    @Select("SELECT * FROM t_sys_user WHERE user_id = #{userId} ")
    User getOne(Long userId);

    @Delete("DELETE FROM t_sys_user WHERE user_id = #{userId} ")
    void delete(Long userId);

    @Insert("INSERT INTO t_sys_user(mobile,password,username,avatar) VALUES(#{mobile},#{password},#{username},#{avatar})")
    void insert(User user);

    @Update("UPDATE t_sys_user SET username=#{username},password=#{password},avatar=#{avatar} WHERE user_id=#{userId} ")
    void update(User user);
}
