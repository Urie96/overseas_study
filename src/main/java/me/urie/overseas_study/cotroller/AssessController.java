package me.urie.overseas_study.cotroller;

import me.urie.overseas_study.bean.AssessBody;
import me.urie.overseas_study.bean.Msg;
import me.urie.overseas_study.mapper.AssessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessController {
    @Autowired
    AssessMapper assessMapper;

    @RequestMapping("/assess_submit")
    public Msg assessNew(AssessBody assessBody) {
        return 1 == assessMapper.insert(assessBody) ? Msg.success() : Msg.fail();
    }

}
