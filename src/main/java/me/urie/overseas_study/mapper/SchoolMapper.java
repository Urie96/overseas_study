package me.urie.overseas_study.mapper;

import me.urie.overseas_study.bean.School;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SchoolMapper {
    @Cacheable(cacheNames = {"countries"})
    @Select("select scountry from school group by scountry")
    List<String> selectCountries();

    @Cacheable(cacheNames = {"hotSchools"})
    @Select("select sname,scountry,snature,scity,sregion,img from school order by hits desc limit 6")
    List<School> getSixSchools();

    @Select("select * from school where sname=#{name}")
    School selectSchoolByName(String name);

    @Cacheable(cacheNames = {"searchSchool"})
    @Select("<script>select * from school where sname like '%${sname}%' <if test=\"scountry!=null\"> and scountry=#{scountry} </if><if test=\"snature!=null\">and snature=#{snature}</if></script>")
    List<School> getSchoolsByCondition(School school);

    @Update("update school set hits=hits+1 where sname=#{name}")
    void addHit(String name);
}
