package me.urie.overseas_study.mapper;

import me.urie.overseas_study.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.Cacheable;

public interface UserMapper {
    @Update("update user set upassword=#{password} where uname=#{name}")
    int passwordUpdate(String name, String password);

    @Select("select * from user where uname=#{name}")
    User getUserByName(String name);

    @Insert("insert into user(uname,upassword,unickname,ugender,uphonenumber,uemail,uQQ)" +
            " values(#{uname},#{upassword},#{unickname},#{ugender},#{uphonenumber},#{uemail},#{uQQ})")
    int insert(User user);

    @Select("select count(*) from user where uname=#{name}")
    int checkName(String name);

    @Select("select count(*) from user where uname=#{name} and upassword=#{password}")
    int match(String name, String password);

    @Cacheable(cacheNames = {"userById"})
    @Select("select * from user where uid=#{id}")
    User getUserById(int id);

    @Select("select uname from user where uid=#{id}")
    String getUnameById(int id);

    @Select("select uname from user where uemail=#{email}")
    String selectEmail(String email);
}
