package me.urie.overseas_study.mapper;

import me.urie.overseas_study.bean.Answer;
import org.apache.ibatis.annotations.Select;

public interface AnswerMapper {
    @Select("select * from answers where qid=#{qid}")
    Answer selectByQid(int qid);
}
