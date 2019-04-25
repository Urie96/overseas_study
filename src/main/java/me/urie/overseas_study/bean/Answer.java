package me.urie.overseas_study.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Answer {
    private Integer aid;

    private Integer qid;

    private String adetail;

    private Date adate;

    private Integer adminid;
}
