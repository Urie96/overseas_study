package me.urie.overseas_study.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter
public class AssessBody implements Serializable {
    private int uid;
    private String uname;
    private String uphone;
    private String uemail;
    private String uqq;
    private String[] countries;
    private String targetEducation;
    private String targetMajor;
    private String studyDate;
    private String budget;
    private String workExperience;
    private String currentEducation;
    private String currentSchoolType;

    public String getCountries() {
        return Arrays.toString(countries);
    }
}