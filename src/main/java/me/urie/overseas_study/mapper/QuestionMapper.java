package me.urie.overseas_study.mapper;

import me.urie.overseas_study.bean.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface QuestionMapper {
    @Insert("insert into questions(qcountry,qsummary,qtype,qdetail,uid,qdate)" +
            " values(#{qcountry},#{qsummary},#{qtype},#{qdetail},#{uid},#{qdate})")
    int insert(Question question);

    @Select("<script>select questions.* from questions,answers where questions.qid=answers.qid" +
            "<if test=\"qsummary!=null and qsummary!=''\"> and qsummary like '%${qsummary}%'</if>" +
            "<if test=\"qtype!='' and qtype!=null\"> and qtype=#{qtype} </if>" +
            "and is_advise=#{hot} order by qdate desc</script>")
    @Results({
            @Result(column = "qid", property = "qid"),
            @Result(column = "qid", property = "answer", one = @One(
                    select = "me.urie.overseas_study.mapper.AnswerMapper.selectByQid"
            )),
            @Result(column = "uid", property = "uname", one = @One(
                    select = "me.urie.overseas_study.mapper.UserMapper.getUnameById"
            ))
    })
    List<Question> selectByCondition(String qsummary, String qtype, int hot);

    @Select("select questions.* from questions,answers" +
            " where questions.qid=answers.qid and questions.uid=#{id}")
    @Results({
            @Result(column = "qid", property = "qid"),
            @Result(column = "qid", property = "answer", one = @One(
                    select = "me.urie.overseas_study.mapper.AnswerMapper.selectByQid"
            ))
    })
    List<Question> selectByUid(int id);

    @Update("update questions set hasread=1 where qid=#{qid}")
    int updateHasread(int qid);

    @Select("select * from questions where uid=#{id} and " +
            "qid not in(select qid from answers)")
    List<Question> selectUnAnsweredQ(int id);

    @Select("select * from questions where is_advise=1 and " +
            "qid in(select qid from answers) order by qdate desc " +
            "limit 30")
    List<Question> selectHotQuestions();
}
