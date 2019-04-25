package me.urie.overseas_study.cotroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.urie.overseas_study.bean.Msg;
import me.urie.overseas_study.bean.Question;
import me.urie.overseas_study.mapper.QuestionMapper;
import me.urie.overseas_study.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class QaController {
    @Autowired
    QuestionMapper questionMapper;

    @RequestMapping("/submit_question")
    public Msg addQuestion(Question question, HttpSession session) {
        int uid = SessionUtils.getUid(session);
        question.setUid(uid);
        question.setQdate(new Date());
        return 1 == questionMapper.insert(question) ? Msg.success() : Msg.fail();
    }

    @RequestMapping("/get_wenda")
    public Msg sendAnsweredQA(String qsummary, String qtype, int new_hot, int pageNo) {
        PageHelper.startPage(pageNo, 5);
        List<Question> questions = questionMapper.selectByCondition(qsummary, qtype, new_hot);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return Msg.success().add("qa_content", questions).add("pageInfo", pageInfo);
    }

    @RequestMapping("/get_answered_qa")
    public Msg sendUsersQA(HttpSession session) {
        int uid = SessionUtils.getUid(session);
        List<Question> questions = questionMapper.selectByUid(uid);
        List<Question> reads = questions.stream()
                .filter(Question::getHasread)
                .collect(Collectors.toList());
        List<Question> unreads = questions.stream()
                .filter(e -> !e.getHasread())
                .collect(Collectors.toList());
        return Msg.success().add("unreadQAs", unreads).add("readQAs", reads);
    }

    @RequestMapping("mark_read_qa")
    public Msg markRead(int qid) {
        return 1 == questionMapper.updateHasread(qid) ? Msg.success() : Msg.fail();
    }

    @RequestMapping("get_unanswered_qa")
    public Msg sendUnAnsweredQA(HttpSession session) {
        int uid = SessionUtils.getUid(session);
        return Msg.success().add("unanswered_q", questionMapper.selectUnAnsweredQ(uid));
    }

    @RequestMapping("get_questions")
    public Msg sendHotQuestions() {
        return Msg.success().add("questions", questionMapper.selectHotQuestions());
    }
}
