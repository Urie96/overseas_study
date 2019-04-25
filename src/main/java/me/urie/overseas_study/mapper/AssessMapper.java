package me.urie.overseas_study.mapper;

import me.urie.overseas_study.bean.AssessBody;
import org.apache.ibatis.annotations.Insert;

public interface AssessMapper {
    @Insert("insert into assess_tb(uid,uname,uphone,uemail,uQQ,countries," +
            "target_education,target_major,study_date,budget,work_experience," +
            "current_education,current_school_type) values (#{uid},#{uname},#{uphone}," +
            "#{uemail},#{uqq},#{countries}," +
            "#{targetEducation},#{targetMajor},#{studyDate}," +
            "#{budget},#{workExperience},#{currentEducation}," +
            "#{currentSchoolType})")
    int insert(AssessBody assessBody);
}
