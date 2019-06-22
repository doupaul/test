package com.mapper;

import com.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO user(id,username,password) VALUE (#{id},#{username}, #{password})")
    int addUser(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteUserById(int id);

    @Update("UPDATE user SET username = #{username},password = #{password} WHERE  id = #{id}")
    int updateUser(User user);

    @Select({"SELECT * FROM user WHERE id=#{id}"})
    User queryById(int id);

    @Select({"SELECT id,username FROM user"})
    List<User> queryAllUser();

    @Select({"SELECT id,username FROM user WHERE username LIKE concat('%',#{username},'%')"})
    List<User> queryUserByName(User user);

    @Select("SELECT * FROM user WHERE username=#{username} AND password=#{password}")
    User findUser(User user);
}
