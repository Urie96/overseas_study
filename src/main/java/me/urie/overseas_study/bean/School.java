package me.urie.overseas_study.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class School implements Serializable {
    private int sid;
    private String sname;
    private String swebsite;
    private String scountry;
    private String snature;
    private String detail;
    private String scity;
    private int sstudentnum;
    private String sregion;
    private String img;
    private int hits;
}
