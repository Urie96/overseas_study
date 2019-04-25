package me.urie.overseas_study.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Question implements Serializable {
    private Integer qid;
    private String qtype;
    private String qsummary;
    private String qdetail;
    private Date qdate;
    private Integer uid;
    private Boolean is_advise;
    private Boolean hasread;
    private String qcountry;
    private Answer answer;
    private String uname;
}
